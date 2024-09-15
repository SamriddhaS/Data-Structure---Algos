#include <stdio.h>
#include <stdlib.h>

struct node
{
    int data;
    node* next;
};

node* head = NULL;
node* last = NULL;

void addNewNode(int value){

    // This allocates memory for a new node and 
    // with data and next pointing to null.
    node* newItem = (node *) malloc(sizeof(node));
    (*newItem).data = value;
    (*newItem).next = NULL;
    
    if (last==NULL&&head==NULL)
    {
        // If it's the first node then point 
        // both head and current to the new node.
        head = newItem;
        last = head;
    }else{
        // If its not a new node then current already 
        // points to the last item of the list. 
        // We just need to change current last item, point 
        // to the new last item, and then assign the newly 
        // created item as current/lastItem 
        last->next = newItem;
        last = newItem;
    }
}

int main() {

    addNewNode(22);
    addNewNode(2);
    addNewNode(89);
    addNewNode(9);

    printf("Below Is your Linked List ===> ");
    node *temp = head;
    while (temp!=NULL)
    {
        printf("\n %d",temp->data);
        temp = temp->next;
    }

    printf("\n");
    
    return 0;
}