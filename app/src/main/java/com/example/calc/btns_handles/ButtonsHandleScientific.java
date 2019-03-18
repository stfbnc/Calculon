package com.example.calc.btns_handles;

import java.util.Locale;

public class ButtonsHandleScientific extends ButtonsHandle{

    protected String sin_str = "sin";
    protected String cos_str = "cos";
    protected String tan_str = "tan";
    protected String ln_str = "ln";
    protected String log_str = "log";
    protected String pi = "π";
    protected String neper = "e";
    {opStrings.put("apxStr", "^");}

    @Override
    public String numberHandle(String nums, String str, boolean eqClick){
        if(nums.equals(neper)){
            nums = "2.78";
        }
        if(nums.equals(pi)){
            nums = "3.14";
        }
        return super.numberHandle(nums, str, eqClick);
    }

    @Override
    public String clearHandle(String str){
        try {
            if (str.substring(str.length() - 1).equals("s")) {
                str = str.substring(0, str.length() - 3);
            } else if (str.substring(str.length() - 1).equals("g")) {
                str = str.substring(0, str.length() - 3);
            } else if (str.substring(str.length() - 1).equals("n")) {
                if (str.substring(str.length() - 2, str.length() - 1).equals("l")) {
                    str = str.substring(0, str.length() - 2);
                } else {
                    str = str.substring(0, str.length() - 3);
                }
            } else {
                str = super.clearHandle(str);
            }
        }catch (StringIndexOutOfBoundsException e){
            str = super.clearHandle(str);
        }
        return str;
    }

    public String sqrtHandle(String str){
        Double res = Double.parseDouble(str);
        res = Math.sqrt(res);
        return String.format(Locale.ENGLISH, "%.8f", res);
    }

    public String mathfunHandle(String fun, String str){
        Double res = Double.parseDouble(str);
        if(fun.equals(sin_str)) {
            res = Math.sin(res);
        }else if(fun.equals(cos_str)){
            res = Math.cos(res);
        }else if(fun.equals(tan_str)){
            res = Math.tan(res);
        }else if(fun.equals(ln_str)){
            res = Math.log(res);
        }else if(fun.equals(log_str)){
            res = Math.log10(res);
        }
        return String.format(Locale.ENGLISH, "%.8f", res);
    }

    public int getMax(int[] idxs){
        int max = idxs[0];
        for(int idx : idxs){
            if(idx > max){
                max = idx;
            }
        }
        return max;
    }

    public int getMin(int[] idxs){
        int min = 0;
        for(int idx : idxs){
            if(idx >= 0){
                min = idx;
                break;
            }
        }
        for(int idx : idxs){
            if(idx >= 0 && idx < min){
                min = idx;
            }
        }
        return min;
    }

    @Override
    public String opPrioritySplit(String str){
        String res = "";
        String[] apxSplit = str.split("[\\\\"+opStrings.get("apxStr")+"]");
        if(apxSplit.length > 1) {
            if(apxSplit.length == 2){
                res = doOps(apxSplit[0], apxSplit[1], opStrings.get("apxStr"));
            }else {
                String rgx = "[" + opStrings.get("plusStr") + opStrings.get("divStr") + opStrings.get("mulStr") + opStrings.get("minStr") + "]";
                int lastIdx, firstIdx = 0, firstIdx_i;
                for (int i = 0; i < apxSplit.length - 1; i++) {
                    if (apxSplit[i].split(rgx).length > 1) {
                        int[] lastIdxs = {apxSplit[i].lastIndexOf(opStrings.get("plusStr")), apxSplit[i].lastIndexOf(opStrings.get("mulStr")), apxSplit[i].lastIndexOf(opStrings.get("divStr")), apxSplit[i].lastIndexOf(opStrings.get("minStr"))};
                        lastIdx = getMax(lastIdxs) + 1;
                        String firstNum = apxSplit[i].substring(lastIdx);
                        int[] firstIdxs = {apxSplit[i + 1].indexOf(opStrings.get("plusStr")), apxSplit[i + 1].indexOf(opStrings.get("mulStr")), apxSplit[i + 1].indexOf(opStrings.get("divStr")), apxSplit[i + 1].indexOf(opStrings.get("minStr"))};
                        firstIdx = getMin(firstIdxs);
                        String secondNum = apxSplit[i + 1].substring(0, firstIdx);
                        if (i == 0) {
                            res += apxSplit[i].substring(0, lastIdx) + doOps(firstNum, secondNum, opStrings.get("apxStr"));
                        } else {
                            int[] firstIdxs_i = {apxSplit[i].indexOf(opStrings.get("plusStr")), apxSplit[i].indexOf(opStrings.get("mulStr")), apxSplit[i].indexOf(opStrings.get("divStr")), apxSplit[i].indexOf(opStrings.get("minStr"))};
                            firstIdx_i = getMin(firstIdxs_i);
                            res += apxSplit[i].substring(firstIdx_i, lastIdx) + doOps(firstNum, secondNum, opStrings.get("apxStr"));
                            if (i == apxSplit.length - 2) {
                                res += apxSplit[apxSplit.length - 1].substring(firstIdx);
                            }
                        }
                    }
                }
            }
        }else{
            res = str;
        }
        return super.opPrioritySplit(res);
    }

    @Override
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
        }else if(op.equals(opStrings.get("apxStr"))){
            res = Math.pow(firstNum, secondNum);
        }
        if(isInt){
            return String.valueOf(res.intValue());
        }else{
            return String.format(Locale.ENGLISH, "%.8f", res);
        }
    }

}
