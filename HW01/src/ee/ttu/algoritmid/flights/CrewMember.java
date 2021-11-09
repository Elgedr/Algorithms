package ee.ttu.algoritmid.flights;

public class CrewMember implements FlightCrewMember {
    Double workExp;
    String name;
    Role role;

    public CrewMember(String name, Role role, Double workExp) {
        this.name = name;
        this.role = role;
        this.workExp = workExp;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Role getRole() {
        return this.role;
    }

    @Override
    public double getWorkExperience() {
        return this.workExp;
    }


//    @Override
//    public int compareTo(FlightCrewMember o) {
//        return o.getName().compareTo(this.getName());
//    }

}


