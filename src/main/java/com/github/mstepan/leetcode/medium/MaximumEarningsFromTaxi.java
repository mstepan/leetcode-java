package com.github.mstepan.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2008. Maximum Earnings From Taxi
 *
 * <p>https://leetcode.com/problems/maximum-earnings-from-taxi/description/
 */
public class MaximumEarningsFromTaxi {

    /**
     * N = total road length
     *
     * <p>K = rides count
     *
     * <p>Time: O(N) => O(K)
     *
     * <p>Space: O(N + K) => O(K)
     */
    public static long maxTaxiEarnings(int totalRoadLength, int[][] rides) {
        assert totalRoadLength > 0;
        assert rides != null;

        if (rides.length == 0) {
            return 0L;
        }

        Map<Integer, List<Ride>> ridesStart = gatherStartTimeRides(rides);

        long[] opt = new long[totalRoadLength + 1];

        for (int ridePos = opt.length - 2; ridePos >= 1; --ridePos) {

            List<Ride> possibleRides = ridesStart.get(ridePos);

            long best = opt[ridePos + 1];

            if (possibleRides != null) {
                for (Ride ride : possibleRides) {
                    assert ridePos == ride.start;
                    best = Math.max(best, (ride.end - ride.start + ride.tips) + opt[ride.end]);
                }
            }

            opt[ridePos] = best;
        }

        return opt[1];
    }

    private static Map<Integer, List<Ride>> gatherStartTimeRides(int[][] rides) {

        Map<Integer, List<Ride>> startTimeRides = new HashMap<>();

        for (int[] singleRide : rides) {
            assert singleRide.length == 3;

            int start = singleRide[0];
            int end = singleRide[1];
            int tips = singleRide[2];

            startTimeRides.compute(
                    start,
                    (keyNotUsed, otherRides) -> {
                        if (otherRides == null) {
                            otherRides = new ArrayList<>();
                        }

                        otherRides.add(new Ride(start, end, tips));

                        return otherRides;
                    });
        }

        return startTimeRides;
    }

    record Ride(int start, int end, int tips) {
        Ride {
            assert start < end;
            assert tips >= 0;
        }
    }
}
