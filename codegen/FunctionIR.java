import instructions.*;

public class FunctionIR {
    private String name;
    private String return_type;
    private String[] ints;
    private String[] floats;
    private ControlFlow control_flow;

    public FunctionIR(String name, String return_type, String[] ints, String[] floats, Instruction[] instructions) {
        this.name = name;
        this.return_type = return_type;
        this.ints = ints;
        this.floats = floats;
        this.control_flow = new ControlFlow(instructions);
    }

    public void run() {
        System.out.println("Calculating livenesses...");
        this.control_flow.calculate_livenesses();
        System.out.println("Done. Debugging...");
        this.control_flow.print_debug();
    }

    public String name() {
        return this.name;
    }
}
