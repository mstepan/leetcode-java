package com.github.mstepan.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1396. Design Underground System
 *
 * <p>https://leetcode.com/problems/design-underground-system/description/
 */
public class UndergroundSystem {

    private final Map<Integer, CheckInEvent> inProgressTrips = new HashMap<>();

    private final Map<String, TripStat> tripsStatistic = new HashMap<>();

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

        tripsStatistic.compute(
                tripKey,
                (keyNotUsed, stat) ->
                        stat == null ? new TripStat(delta) : stat.updateOneTrip(delta));
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

    private static final class TripStat {
        long totalTime;
        long totalCount;

        TripStat(long delta) {
            totalTime = delta;
            totalCount = 1;
        }

        public TripStat updateOneTrip(long delta) {
            totalTime += delta;
            ++totalCount;

            return this;
        }

        public double calculateAverage() {
            return ((double) totalTime) / totalCount;
        }
    }
}
