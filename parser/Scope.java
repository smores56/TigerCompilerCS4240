import java.util.*;

public class Scope{
    public String name;
    public Map<String, String> types;
    public Map<String,String> variables;
    public ArrayList<String> assignments;
    public ArrayList<String> ops;


    public Scope(String n){
        this.name = n;
        this.types = new HashMap<>();
        this.variables = new HashMap<>();
        this.assignments = new ArrayList<>();
        this.ops = new ArrayList<>();
    }

    public void addType(String name, String type, String count){
        if(count.equals("0")){
            this.types.put(name, type);
        } else {
            this.types.put(name, type+"["+count+"]");
        }
    }

    public void addVariable(String name, String type){
        this.variables.put(name, type);
    }

    public void addAssignment(String var, String val){
        String retVal;
        String type = this.variables.get(var);

        if(!(type.equals("int")) && !(type.equals("float"))){
            type = this.types.get(type);
        }
        if(type.contains("[")){
            retVal = "assign, "+var+", "+type.split("\\[")[1].substring(0,type.split("\\[")[1].length() - 1)+", "+val;
        } else {
            retVal = "assign, "+var+", "+val+",";
        }
        this.assignments.add(retVal);
    }

    public void addOp(String s){
        this.ops.add(s);
    }

    public String string(){
        String retVal = "#start_function "+this.name+"\n";
        retVal += "void "+this.name+"():\n";
        ArrayList<String> ints = new ArrayList<>();
        ArrayList<String> floats = new ArrayList<>();

        for(String s: variables.keySet()){
            String t = variables.get(s);

            if(t.equals("int")){
                ints.add(s);
            } else if (t.equals("float")){
                floats.add(s);
            } else {
                t = this.types.get(t);
                if(t.contains("[")){
                    s += "[" + t.split("\\[")[1];
                }
                if(t.contains("int")){
                    ints.add(s);
                } else {
                    floats.add(s);
                }
            }
        }

        retVal += "int-list: ";
        for(String s : ints){
            retVal += s+", ";
        }
        retVal += "\nfloat-list: ";
        for(String s : floats){
            retVal += s+", ";
        }
        retVal += "\n";

        for(String s : this.assignments){
            retVal += "\t"+s+"\n";
        }

        if(this.name == "main"){
            retVal += "main:\n";
        }
        for(String s : this.ops) {
            retVal += "\t"+s+"\n";
        }

        retVal += "#end_function "+this.name+"\n";

        return retVal;

    }


}
