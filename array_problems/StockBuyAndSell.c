#include <stdio.h>
#include <stdlib.h>


int maxProfit(int prices[],int size) {
        int buyPrice=0;
        int sellingPrice=0;
        
        for(int i=0;i<size-1;i++){
            if(buyPrice==0 || prices[i]<buyPrice){
                buyPrice = prices[i];
            }
            printf("This is loop %d",i);
            printf("%d",prices[i]);
            printf("%d",buyPrice);
            printf("%d",sellingPrice);
            if(prices[i]>sellingPrice && prices[i]>buyPrice){
                sellingPrice = prices[i];
            }
        }
        
        if(sellingPrice>buyPrice) return sellingPrice-buyPrice;
        else return 0;
}

int main(){
    int arry[2] = {1,2};
    maxProfit(arry,2);
}