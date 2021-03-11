package com.data.classcheck_in.util;

import com.data.classcheck_in.model.Display;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    //快速排序
    public static List<Display> quickSort(List<Display> displayList){
        Display[] displays = new Display[displayList.size()];
        int i=0;
        for (Display display:displayList){
            displays[i] = display;
            i++;
        }
        quickSort(displays,0,displayList.size()-1);
        List<Display> result = new ArrayList<>();
        for (int j=0;j<displays.length;j++){
            result.add(displays[j]);
        }
        return result;
    }

    private static void quickSort(Display[] displays,int begin,int end){
        if (begin>=0 && begin<end && end<displays.length){
            int i=begin,j=end;
            Long checkinNo = displays[i].getCheckinNo();
            Display x = displays[i];
            while (i!=j){
                while (i<j && displays[j].getCheckinNo()>=checkinNo){
                    j--;
                }
                if (i<j){
                    displays[i++] = displays[j];
                }
                while (i<j && displays[j].getCheckinNo()<=checkinNo){
                    i++;
                }
                if (i<j){
                    displays[j--]=displays[i];
                }
            }
            displays[i]=x;
            quickSort(displays,begin,j-1);
            quickSort(displays,i+1,end);
        }
    }
}
