#include <stdio.h>
#include <stdlib.h>

struct node
{
    int data;
    node *next;
};

node *head = NULL;
node *last = NULL;

void addNewNodeAtFirst(int value)
{
    if (last == NULL && head == NULL)
    {
        // If it's the first node then point
        // both head and current to the new node.
        node *newItem = (node *)malloc(sizeof(node));
        (*newItem).data = value;
        (*newItem).next = NULL;
        head = newItem;
    }
    else
    {
        node *newItem = (node *)malloc(sizeof(node));
        (*newItem).data = value;
        (*newItem).next = head;
        head = newItem;
    }
}

void addNewNodeAtLast(int value)
{

    // This allocates memory for a new node and
    // with data and next pointing to null.
    node *newItem = (node *)malloc(sizeof(node));
    (*newItem).data = value;
    (*newItem).next = NULL;

    if (last == NULL && head == NULL)
    {
        // If it's the first node then point
        // both head and current to the new node.
        head = newItem;
        last = head;
    }
    else
    {
        // If its not a new node then current already
        // points to the last item of the list.
        // We just need to change current last item, point
        // to the new last item, and then assign the newly
        // created item as current/lastItem
        last->next = newItem;
        last = newItem;
    }
}

void insertAtAnyposition(int position, int value);
void printList();

int main()
{
    addNewNodeAtLast(20);
    addNewNodeAtLast(19);
    addNewNodeAtLast(18);
    addNewNodeAtLast(17);
    addNewNodeAtLast(16);
    addNewNodeAtLast(15);
    addNewNodeAtFirst(14);

    insertAtAnyposition(3,1800);
    insertAtAnyposition(6,707);
    insertAtAnyposition(9,7099);

    printList();

    insertAtAnyposition(100,909);
    printList();
    return 0;
}

void insertAtAnyposition(int position, int value){

    if (position==1)
    {
        addNewNodeAtFirst(value);
        return;
    }

    // use this node to point to the position-1 node.
    node *pos_1 = head; 
    for (int i = 0; i < position-2; i++)
    {
        if (pos_1->next==NULL)
        {
            printf("\nInvalid Position\n");
            return;
        }
        
        pos_1 = pos_1->next;
    }

    node *newItem = (node *)malloc(sizeof(node));
    newItem->data = value;
    newItem->next = NULL;

    newItem->next = pos_1->next;
    pos_1->next = newItem;

}

void printList(){
    printf("Below Is your Linked List ===> ");
    node *temp = head;
    while (temp != NULL)
    {
        printf("\n %d", temp->data);
        temp = temp->next;
    }

    printf("\n");
}