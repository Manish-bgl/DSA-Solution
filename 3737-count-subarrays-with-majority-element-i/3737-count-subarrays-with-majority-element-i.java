class Solution {
    public int countMajoritySubarrays(int[] arr, int target) {
        int n=arr.length;
        int ans=0;
        for(int i=0;i<n;i++){
            int count=0;

            for(int j=i;j<n;j++){
                if(arr[j]==target){
                    count++;
                }
                int  len=j-i+1;
                if(count>len/2){
                    ans++;
                }
            }

        }
        return ans;
        
    }
}