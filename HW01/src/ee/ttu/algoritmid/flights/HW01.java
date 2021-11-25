package ee.ttu.algoritmid.flights;

import java.util.Comparator;
import java.util.List;

public class HW01 implements FlightCrewRegistrationSystem {
    private BinaryTree pilotTree = new BinaryTree();
    private BinaryTree copilotTree = new BinaryTree();
    private BinaryTree stuardTree = new BinaryTree();
    private Crew currentCrew;

    /**
     * Register an arriving new crew member to a flight (try to find them a team from a queue).
     *
     * @param participant participant
     * @return crew
     * @throws IllegalArgumentException exception
     */
    @Override
    public FlightCrew registerToFlight(FlightCrewMember participant) throws IllegalArgumentException {
        if (participant == null || participant.getName() == null || participant.getRole() == null || participant.getWorkExperience() < 0 || participant.getName().isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            currentCrew = null;
            findTeam(participant);
        }
        if (currentCrew.getFlightAttendant() != null &&
                currentCrew.getCopilot() != null && currentCrew.getPilot() != null) {
            pilotTree.deleteMember(currentCrew.getPilot());
            copilotTree.deleteMember(currentCrew.getCopilot());
            stuardTree.deleteMember(currentCrew.getFlightAttendant());
            return currentCrew;
        } else {
            if (participant.getRole().equals(FlightCrewMember.Role.COPILOT)) {
                copilotTree.addMember(participant);
            } else if (participant.getRole().equals(FlightCrewMember.Role.FLIGHT_ATTENDANT)) {
                stuardTree.addMember(participant);
            } else {
                pilotTree.addMember(participant);
            }
            return null;
        }
    }

    @Override
    public List<FlightCrewMember> crewMembersWithoutTeam() {
        List<FlightCrewMember> allMembers = pilotTree.getAllMembers();
        allMembers.addAll(copilotTree.getAllMembers());
        allMembers.addAll(stuardTree.getAllMembers());
        allMembers.sort(Comparator.comparing(FlightCrewMember::getWorkExperience).thenComparing(new MyComparator()));
        return allMembers;
    }

    /**
     * Find a crew that will be suitable for the new member.
     *
     * @param member just joined
     * @return boolean (found the team or not)
     */
    public boolean findTeam(FlightCrewMember member) {
        currentCrew = new Crew();
        if (member.getRole().equals(FlightCrewMember.Role.FLIGHT_ATTENDANT)) {
            return findTeamForAttendant(member);
        } else if (member.getRole().equals(FlightCrewMember.Role.COPILOT)) {
            return findTeamForCopilot(member);
        } else {
            return findTeamForPilot(member);
        }
    }

    /**
     * Find a suitable team for a new pilot.
     * @param member pilot
     * @return true if the team was found
     */
    private boolean findTeamForPilot(FlightCrewMember member) {
        currentCrew.setPilot(member);
        double minCopilotExp = member.getWorkExperience() - 10;
        double maxCopilotExp = member.getWorkExperience() - 5;
        FlightCrewMember copilot = this.copilotTree.findValueInRange(minCopilotExp, maxCopilotExp, true);
        FlightCrewMember stuart = null;
        if (copilot != null) {
            stuart = this.stuardTree.findValueLessOrEqualN(copilot.getWorkExperience() - 3);
        }
        if (stuart != null) {
            currentCrew.setStuart(stuart);
            currentCrew.setCopilot(copilot);
            return true;
        }
        return false;
    }

    /**
     * Find a suitable team for the copilot.
     * @param member copilot
     * @return true if a suitable team was found
     */
    public boolean findTeamForCopilot(FlightCrewMember member) {
        currentCrew.setCopilot(member);
        double minPilotExp = member.getWorkExperience() + 5;
        double maxPilotExp = member.getWorkExperience() + 10;
        FlightCrewMember pilot = this.pilotTree.findValueInRange(minPilotExp, maxPilotExp, false);
        FlightCrewMember stuart = null;
        if (pilot != null) {
            stuart = this.stuardTree.findValueLessOrEqualN(member.getWorkExperience() - 3);
        }
        if (stuart != null) {
            currentCrew.setPilot(pilot);
            currentCrew.setStuart(stuart);
            return true;
        }
        return false;
    }

    /**
     * Find a suitable team for atendant.
     * @param member attendant
     * @return true if a team was found
     */
    public boolean findTeamForAttendant(FlightCrewMember member) {
        currentCrew.setStuart(member);
        FlightCrewMember copilot = this.copilotTree.findValueMoreOrEqualN(member.getWorkExperience() + 3);
        FlightCrewMember pilot = null;
        if (copilot != null) {
            double minPilotExp = copilot.getWorkExperience() + 5;
            double maxPilotExp = copilot.getWorkExperience() + 10;
            pilot = this.pilotTree.findValueInRange(minPilotExp, maxPilotExp, false);
        }
        if (pilot != null) {
            currentCrew.setPilot(pilot);
            currentCrew.setCopilot(copilot);
            return true;
        }
        return false;
    }
}
