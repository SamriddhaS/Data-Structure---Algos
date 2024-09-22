#include <stdio.h>
#include <stdlib.h>

struct node
{
    int data;
    node *next;
};

node *head = NULL;
node *last = NULL;

void addNewNodeAtFirst(int value);
void addNewNodeAtLast(int value);
void insertAtAnyposition(int position, int value);
void printList();
void deleteNodeAtN();

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

    deleteNodeAtN();

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

void deleteNodeAtN(){
    int positionToDelete = -1;
    printf("Please ennter the position that you want to delete");
    scanf("%d",&positionToDelete);
    if (positionToDelete<1){
        printf("Invaild position...");
        return;
    }
    
    printf("List before deleting --> ");
    printList();

    node *pos_1 = head; // For pointing to n-1 position.

    if (positionToDelete==1)
    {
        // If we are deleting the first position 
        // no need to use a loop just point the head to 
        // next node and delete the pos_1 node.
        head = pos_1->next;
        free(pos_1);
        printf("List After deleting --> ");
        printList();
        return;
    }
    
    node *pos_2 = pos_1; // For pointing to nth position.

    for (int i = 0; i < positionToDelete-2; i++)
    {
        pos_1 = pos_1->next;
        if (pos_1->next==NULL)
        {
            printf("\nInvalid Position\n");
            return;
        }
    }

    /*
    Buffered Output in printf():

    In C, printf() does not immediately print the output to the console. Instead, the output is stored in an output buffer, and it is printed out (flushed) either:
        When a newline character (\n) is encountered.
        When the buffer is full.
        When the program terminates normally.
        Or when you explicitly flush the output buffer using fflush(stdout).
    */
    printf("We are getting exception here...When positionToDelete == length of the list+1");
    fflush(stdout); // Force the output to be flushed immediately

    pos_2 = pos_1->next; // pointing the nth node.
    printf("before change");
    pos_1->next = pos_2->next; // Change the next pointer of n-1 th node to n+1 position 
    free(pos_2); // removing the nth node once we have changed the pointer of n-1.

    printf("List After deleting");
    printList();

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