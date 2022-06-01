//Maxim Kim
import java.util.ArrayList;
import java.util.List;

public class Sorting {

    //Goes through list, finds smallest, and swaps with first element.
    //Repeat this starting from a new first element, which is 1 index greater.
    public static void selectionSort(List nums) {
        for (int r = 0; r < nums.size() - 1; r++) {
            int smallestIndex = r;
            Comparable smallest = (Comparable)(nums.get(smallestIndex));
            for (int c = r + 1; c < nums.size(); c++) {
                if (smallest.compareTo(nums.get(c))>0){ //if selected element is smaller than current smallest
                    smallest = (Comparable)(nums.get(c));
                    smallestIndex = c;
                }
            }
            if (smallest != nums.get(r)) { //if first element is not smallest
                nums.set(smallestIndex, nums.get(r));
                nums.set(r, smallest);
            }
        }
    }


    //Goes through list and compares selected element with elements to the left.
    //Checks elements to left and shifts elements over until left element is less than selected element
    //Selected element then goes 1 index more than left element
    public static void insertionSort(List nums) {
        for (int r = 0; r < nums.size(); r++) {
            int comparorIndex = r - 1;
            Comparable number = (Comparable)(nums.get(r));
            while (comparorIndex >= 0 && number.compareTo(nums.get(comparorIndex))<0){
                nums.set(comparorIndex + 1, nums.get(comparorIndex));
                comparorIndex--;
            }
            nums.set(comparorIndex + 1, number); //set element at the gap
        }
    }

    //Goes through list and compares the selected element with the element infront to see if in order
    //If the element infront is smaller, swap the 2 elements and proceed
    //Repeat this process until the list is sorted
    public static void bubbleSort(List nums) {
        boolean sorted = false;
        while (!sorted) {
            sorted=true;
            for (int i = 0; i < nums.size() - 1; i++) {
                Comparable thing1 = (Comparable) nums.get(i);
                Comparable thing2 = (Comparable) nums.get(i + 1);
                if (thing1.compareTo(thing2) > 0) {
                    nums.set(i, thing2);
                    nums.set(i + 1, thing1);
                    sorted=false;
                }
            }
        }
    }






    public static void quickSort(List<Integer> nums) { //these dont work
        int left=0;
        int right=nums.size()-1;
        int middle=right/2;
        quickSorter(nums,left,right,middle);
    }
    public static void quickSorter(List<Integer> nums, int left, int middle, int right) { //doesn't work
        if (left == right){
            return;
        }
        int pivot=nums.get(left);
        while (left < right) {
            while (nums.get(left) < pivot) { //will stop when greater than pivot (out of place)
                left++;
            }
            while (nums.get(right) > pivot) { //will stop when less than pivot (out of place)
                right--;
            }
            int leftValue = nums.get(left);
            //swap left and right elements
            nums.set(left, nums.get(right));
            nums.set(right, leftValue);
            left++;
            right--;
            //Swaps before checking whether left<right fix later
        }
        //print(nums,"First: ");
        quickSorter(nums,left,middle/2,middle);
        quickSorter(nums,middle+1,1+right-middle/2,right);
    }


    //GRADE THIS ONE ANDREW
    //Splits list in half until size of each smaller list is 1
    //Merges and sorts smaller lists together
    public static List mergeSort(List nums){
        if (nums.size()<=1){ //base case
            return nums;
        }
        List left = new ArrayList(); //left half of given list
        List right = new ArrayList(); //right half of given list
        for (int i = 0; i < nums.size() / 2; i++) {
            left.add(nums.get(i));
        }
        for (int p = nums.size() / 2; p < nums.size(); p++) {
            right.add(nums.get(p));
        }
        //System.out.println("Right: "+ right);
        List right2 = mergeSort(right);
        //System.out.println("Left: "+ left);
        List left2 = mergeSort(left);

        int num1 = 0;
        int num2 = 0;
        List newNums = new ArrayList();
        while (num1 < left2.size() && num2 < right2.size()) {
            Comparable leftThing = (Comparable)(left2.get(num1));
            Comparable rightThing = (Comparable)(right2.get(num2));
            if (leftThing.compareTo(rightThing)<=0){
                newNums.add(leftThing);
                num1++;
            } else if (leftThing.compareTo(rightThing)>0){
                newNums.add(rightThing);
                num2++;
            }
        }
        while (num1<left2.size()){
            newNums.add(left2.get(num1));
            num1++;
        }
        while (num2<right2.size()){
            newNums.add(right2.get(num2));
            num2++;
        }
        return newNums;
    }
}
