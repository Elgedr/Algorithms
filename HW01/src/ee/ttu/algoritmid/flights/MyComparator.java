package ee.ttu.algoritmid.flights;

import java.util.Comparator;

public class MyComparator implements Comparator<FlightCrewMember> {

    @Override
    public int compare(FlightCrewMember o1, FlightCrewMember o2) {
        return Integer.compare(getAssignedValue(o1.getRole()), getAssignedValue(o2.getRole()));
    }

    /**
     * Get a custom value.
     * @param role role
     * @return int
     */
    public int getAssignedValue(FlightCrewMember.Role role) {
        return switch (role) {
            case FLIGHT_ATTENDANT -> 0;
            case COPILOT -> 1;
            case PILOT -> 2;
        };
    }
}
