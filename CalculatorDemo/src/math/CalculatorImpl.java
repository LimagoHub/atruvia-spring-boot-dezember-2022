package math;

public class CalculatorImpl implements Calculator {



    @Override

    // @RoleAllowed(admin)
    public double add(double a, double b) {
       return a + b;
    }
    @Override

    // @RoleAllowed(gast)
    // RunAs(Admin)
    public double sub(double a, double b) {
        return this.add(a, -b);
    }

}
