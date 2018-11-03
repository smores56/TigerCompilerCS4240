import java.util.*;

public class Scope{
  public String name;
  public String retType;
  public Map<String, String> types;
  public Map<String,String> variables;
  public ArrayList<String> assignments;
  public ArrayList<String> ops;
  public ArrayList<String> params;
  public int tempReg;
  public int loopReg;


  public Scope(String n, String t){
    this.name = n;
    this.tempReg = 0;
    this.loopReg = 0;
    this.retType = t;
    this.types = new HashMap<>();
    this.variables = new HashMap<>();
    this.assignments = new ArrayList<>();
    this.ops = new ArrayList<>();
    this.params = new ArrayList<>();
  }

  public String newReg(){
      String r = "$t"+this.tempReg;
      this.tempReg++;
      return r;
  }
  public String Reg(){
      return "$t"+this.tempReg;
  }

  public void addType(String name, String type, String count){
    if(count.equals("0")){
      this.types.put(name, type);
    }
    else{
      this.types.put(name, type+"["+count+"]");
    }
  }

  public void addVariable(String name, String type){
    this.variables.put(name, type);
  }

  public void addParam(String name, String type){
    this.variables.put(name, type);
    this.params.add(name);
  }

  public String addLoop(){
      String s = "label_"+this.loopReg;
      this.loopReg++;
      this.ops.add(s+":");
      return s;
  }

  public void addAssignment(String var, String val){
    String retVal;
    String type = this.variables.get(var);
    if(!(type.equals("int")) && !(type.equals("float"))){
      type = this.types.get(type);
    }
    if(type.contains("[")){
      retVal = "assign, "+var+", "+type.split("\\[")[1].substring(0,type.split("\\[")[1].length() - 1)+", "+val;
    }
    else{
      retVal = "assign, "+var+", "+val+",";
    }
    this.assignments.add(retVal);
  }

  public void addOp(String s){
    this.ops.add(s);
  }

  public String string(){
    String retVal = "#start_function "+this.name+"\n";

    String params = "";
    for(String s : this.params){
      String t = this.variables.get(s);
      params += t+" "+s+",";
    }
    if(params.length() > 0){params = params.substring(0,params.length()-1);}

    retVal += this.retType+" "+this.name+"("+params+"):\n";
    ArrayList<String> ints = new ArrayList<>();
    ArrayList<String> floats = new ArrayList<>();

    for(String s: variables.keySet()){
      String t = variables.get(s);

      if(t.equals("int")){
        ints.add(s);
      } else if (t.equals("float")){
        floats.add(s);
      } else{
        t = this.types.get(t);
        if(t.contains("[")){
          s += "[" + t.split("\\[")[1];
        }
        if(t.contains("int")){
          ints.add(s);
        }
        else{
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
        String tab = (s.startsWith("label_")) ? "" : "\t";
      retVal += tab+s+"\n";
    }

    retVal += "#end_function "+this.name+"\n";

    return retVal;

  }


}
