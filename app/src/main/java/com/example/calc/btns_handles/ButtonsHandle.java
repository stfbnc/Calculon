package com.example.calc.btns_handles;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ButtonsHandle{

    protected String emptyStr = "";
    protected String dotStr = ".";
    protected Map<String, String> opStrings = new HashMap<>();
    {opStrings.put("plusStr", "+");}
    {opStrings.put("divStr", "/");}
    {opStrings.put("mulStr", "x");}
    {opStrings.put("minStr", "-");}

    public boolean chkOps(String str){
        boolean chk = false;
        for(Map.Entry<String, String> op : opStrings.entrySet()){
            if(str.substring(str.length()-1).equals(op.getValue())){
                chk = true;
                break;
            }
        }
        return chk;
    }

    public String opHandle(String nums, String str, boolean eqClick) {
        if(!str.equals(emptyStr) && !str.substring(str.length()-1).equals(dotStr)){
            boolean chk = chkOps(str);
            if(eqClick){
                str += nums;
            } else if(chk){
                str = str.substring(0, str.length()-1) + nums;
            } else {
                str += nums;
            }
        }
        return str;
    }

    public String dotHandle(String nums, String str, boolean eqClick){
        if(str.length() == 0){
            if(str.equals(emptyStr)) {
                str = "0" + nums;
            }
        }else{
            if(eqClick){
                str = "0" + nums;
            }else{
                boolean chk = chkOps(str);
                if(chk){
                    str += "0" + nums;
                }else{
                    String rgx = "\\[";
                    for(Map.Entry<String, String> op : opStrings.entrySet()){
                        rgx += op.getValue();
                    }
                    rgx += "]";
                    String[] numbers = str.split(rgx);
                    if(!numbers[numbers.length-1].contains(dotStr)){
                        str += nums;
                    }
                }
            }
        }
        return str;
    }

    public String numberHandle(String nums, String str, boolean eqClick){
        if(str.equals(emptyStr) || eqClick){
            str = nums;
        }else{
            str += nums;
        }
        return str;
    }

    public String equalHandle(String str){
        if(chkOps(str)){
            return str;
        }else{
            return opPrioritySplit(str);
        }
    }

    public String opPrioritySplit(String str){
        String res = "";
        String[] plusSplit = str.split("["+opStrings.get("plusStr")+"]");
        for(int i = 0; i < plusSplit.length; i++){
            String[] minSplit = plusSplit[i].split("["+opStrings.get("minStr")+"]");
            for(int j = 0; j < minSplit.length; j++){
                if(minSplit[j].contains(opStrings.get("mulStr")) || minSplit[j].contains(opStrings.get("divStr"))){
                    res += findStringRes(minSplit[j], "["+opStrings.get("divStr")+opStrings.get("mulStr")+"]");
                }else{
                    res += minSplit[j];
                }
                if(j != minSplit.length-1)
                    res += opStrings.get("minStr");
            }
            if(i != plusSplit.length-1)
                res += opStrings.get("plusStr");
        }
        return findStringRes(res, "["+opStrings.get("plusStr")+opStrings.get("minStr")+"]");
    }

    public String findStringRes(String str, String rgx){
        String[] numbers = str.split(rgx);
        String[] operations = str.split("\\d+\\.*\\d*");
        String result = doOps("0", numbers[0], opStrings.get("plusStr"));
        for(int k = 1; k < operations.length; k++)
            result = doOps(result, numbers[k], operations[k]);
        return result;
    }

    public String doOps(String num1, String num2, String op){
        boolean isInt;
        if(num1.contains(dotStr) || num2.contains(dotStr)){
            isInt = false;
        }else{
            isInt = true;
        }
        Double firstNum = Double.parseDouble(num1);
        Double secondNum = Double.parseDouble(num2);
        Double res = 0.0;
        if(op.equals(opStrings.get("plusStr"))){
            res = firstNum + secondNum;
        }else if(op.equals(opStrings.get("minStr"))){
            res = firstNum - secondNum;
        }else if(op.equals(opStrings.get("mulStr"))){
            res = firstNum * secondNum;
        }else if(op.equals(opStrings.get("divStr"))){
            res = firstNum / secondNum;
            if(firstNum % secondNum != 0){
                isInt = false;
            }
        }
        if(isInt){
            return String.valueOf(res.intValue());
        }else{
            return String.format(Locale.ENGLISH, "%.8f", res);
        }
    }

    public String clearHandle(String str){
        try{
            if(str.equals("NaN") || str.equals("Infinity")){
                str = clearAllHandle();
            }else {
                str = str.substring(0, str.length() - 1);
            }
        }catch(StringIndexOutOfBoundsException e){
            str = emptyStr;
        }
        return str;
    }

    public String clearAllHandle(){
        return emptyStr;
    }

}
