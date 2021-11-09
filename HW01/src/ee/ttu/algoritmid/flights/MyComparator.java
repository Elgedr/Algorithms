package ee.ttu.algoritmid.flights;

import java.util.Comparator;

public class MyComparator implements Comparator<FlightCrewMember> {

    @Override
    public int compare(FlightCrewMember o1, FlightCrewMember o2) {


        return Integer.compare(getAssignedValue(o1.getRole()), getAssignedValue(o2.getRole()));
    }

    /**
     *
     * @param role role
     * @return int
     */
    int getAssignedValue(FlightCrewMember.Role role) {
        switch (role) {
            case FLIGHT_ATTENDANT:
                return 0;
            case COPILOT:
                return 1;
            case PILOT:
                return 2;
            default:
                return Integer.MAX_VALUE;
        }
    }
}
