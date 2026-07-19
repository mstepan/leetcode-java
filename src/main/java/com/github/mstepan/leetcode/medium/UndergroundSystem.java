package com.github.mstepan.leetcode.medium;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1396. Design Underground System
 *
 * <p>https://leetcode.com/problems/design-underground-system/description/
 *
 * <p>Also fully thread safe and non-blocking.
 */
public class UndergroundSystem {

    private final ConcurrentMap<Integer, CheckInEvent> inProgressTrips = new ConcurrentHashMap<>();

    private final ConcurrentMap<String, TripStat> tripsStatistic = new ConcurrentHashMap<>() {};

    public UndergroundSystem() {}

    public void checkIn(int id, String stationName, int startTime) {
        boolean wasNew =
                inProgressTrips.putIfAbsent(id, new CheckInEvent(stationName, startTime)) == null;

        if (!wasNew) {
            throw new IllegalStateException("Double check-in detected for user id + " + id);
        }
    }

    public void checkOut(int id, String destStation, int endTime) {
        CheckInEvent checkInEvent = inProgressTrips.remove(id);

        if (checkInEvent == null) {
            throw new IllegalStateException("Checkout without checking for user id + " + id);
        }

        final String srcStation = checkInEvent.stationName();

        if (srcStation.equals(destStation)) {
            throw new IllegalStateException(
                    "Invalid checkout, src and dest stations are the same: " + srcStation);
        }

        final String tripKey = tripKey(srcStation, destStation);
        final long delta = endTime - checkInEvent.startTime();

        // 'compute' may block threads that interacts with the same bucket, so better to use
        // 'putIfAbsent'
        //        tripsStatistic.compute(
        //                tripKey,
        //                (keyNotUsed, stat) ->
        //                        stat == null ? new TripStat(delta) : stat.updateOneTrip(delta));

        TripStat stat = tripsStatistic.get(tripKey);
        if (stat == null) {
            TripStat candidate = new TripStat(delta);
            TripStat existing = tripsStatistic.putIfAbsent(tripKey, candidate);
            if (existing == null) {
                return; // candidate already contains this trip
            }
            stat = existing;
        }
        stat.updateOneTrip(delta);
    }

    public double getAverageTime(String startStation, String endStation) {

        final String tripKey = tripKey(startStation, endStation);

        TripStat tripStat = tripsStatistic.get(tripKey);

        if (tripStat == null) {
            throw new IllegalStateException("No statistics available yet for trip: " + tripKey);
        }

        return tripStat.calculateAverage();
    }

    private static String tripKey(String srcStation, String destStation) {
        return srcStation + "-" + destStation;
    }

    record CheckInEvent(String stationName, int startTime) {}

    private record TripStat(AtomicReference<SumAndCount> reference) {

        private TripStat(long reference) {
            this(new AtomicReference<>(new SumAndCount(reference, 1)));
        }

        public TripStat updateOneTrip(long delta) {
            reference.updateAndGet(
                    sumAndCount -> new SumAndCount(sumAndCount.sum + delta, sumAndCount.count + 1));

            return this;
        }

        public double calculateAverage() {
            final SumAndCount snapshot = reference.get();

            return ((double) snapshot.sum()) / snapshot.count();
        }
    }

    record SumAndCount(long sum, long count) {}
}
