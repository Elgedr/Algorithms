package ee.ttu.algoritmid.flights;

/**
 * A custom crew class implementing a given interface.
 */
public class Crew implements FlightCrew {
    private FlightCrewMember pilot;
    private FlightCrewMember copilot;
    private FlightCrewMember stuart;

    public Crew() {
    }

    @Override
    public FlightCrewMember getPilot() {
        return pilot;
    }

    @Override
    public FlightCrewMember getCopilot() {
        return copilot;
    }

    @Override
    public FlightCrewMember getFlightAttendant() {
        return stuart;
    }

    public void setPilot(FlightCrewMember pilot) {
        this.pilot = pilot;
    }

    public void setCopilot(FlightCrewMember copilot) {
        this.copilot = copilot;
    }

    public void setStuart(FlightCrewMember stuart) {
        this.stuart = stuart;
    }
}
