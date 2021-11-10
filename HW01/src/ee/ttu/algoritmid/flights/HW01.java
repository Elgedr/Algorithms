package ee.ttu.algoritmid.flights;

import java.util.Comparator;
import java.util.List;

public class HW01 implements FlightCrewRegistrationSystem {
    BinaryTree pilotTree = new BinaryTree();
    BinaryTree copilotTree = new BinaryTree();
    BinaryTree stuardTree = new BinaryTree();
    Crew currentCrew;

    /**
     * Register an arriving new crew member to a flight (try to find them a team from a queue).
     *
     * @param participant
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public FlightCrew registerToFlight(FlightCrewMember participant) throws IllegalArgumentException {
        if (participant == null || participant.getName() == null || participant.getRole() == null
                || participant.getName().isEmpty()
                || participant.getWorkExperience() <= 0) {
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
            currentCrew.setStuart(member);
            System.out.println("Stuart experience: " + member.getWorkExperience());
            FlightCrewMember copilot = this.copilotTree.findValueMoreOrEqualN(member.getWorkExperience() + 3);
            FlightCrewMember pilot = null;
            if (copilot != null) {
                System.out.println("Copilot experience: " + copilot.getWorkExperience());
                double minPilotExp = copilot.getWorkExperience() + 5;
                double maxPilotExp = copilot.getWorkExperience() + 10;
                pilot = this.pilotTree.findValueInRange(minPilotExp, maxPilotExp);
            }
            if (pilot != null) {
                System.out.println("Pilot experience: " + pilot.getWorkExperience());
                currentCrew.setPilot(pilot);
                currentCrew.setCopilot(copilot);
                return true;
            }
        } else if (member.getRole().equals(FlightCrewMember.Role.COPILOT)) {
            currentCrew.setCopilot(member);
            System.out.println("Copilot experience: " + member.getWorkExperience());
            double minPilotExp = member.getWorkExperience() + 5;
            double maxPilotExp = member.getWorkExperience() + 10;
            FlightCrewMember pilot = this.pilotTree.findValueInRange(minPilotExp, maxPilotExp);
            FlightCrewMember stuart = null;
            if (pilot != null) {
                stuart = this.stuardTree.findValueLessOrEqualN(member.getWorkExperience() - 3);
                System.out.println("Pilot experience: " + pilot.getWorkExperience());
            }
            if (stuart != null) {
                System.out.println("Stuart experience: " + stuart.getWorkExperience());
                currentCrew.setPilot(pilot);
                currentCrew.setStuart(stuart);
                return true;
            }
        } else {
            currentCrew.setPilot(member);
            System.out.println("Pilot experience: " + member.getWorkExperience());
            double minCopilotExp = member.getWorkExperience() - 10;
            double maxCopilotExp = member.getWorkExperience() - 5;
            FlightCrewMember copilot = this.copilotTree.findValueInRange(minCopilotExp, maxCopilotExp);
            System.out.println(copilot);
            FlightCrewMember stuart = null;
            if (copilot != null) {
                stuart = this.stuardTree.findValueLessOrEqualN(copilot.getWorkExperience() - 3);
                System.out.println("Copilot experience: " + copilot.getWorkExperience());
            }
            if (stuart != null) {
                currentCrew.setStuart(stuart);
                currentCrew.setCopilot(copilot);
                System.out.println("Stuart experience: " + stuart.getWorkExperience());
                return true;
            }
        }
        return false;
    }
}
