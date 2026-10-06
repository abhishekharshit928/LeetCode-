class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
                int carry = 0;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
ListNode dummy = new ListNode(0);
ListNode temp = dummy;
        while(temp1 != null || temp2 != null || carry != 0){
         int x = (temp1 != null) ? temp1.val : 0;
          int y = (temp2 != null) ? temp2.val : 0;

          int sum = x + y + carry;

          carry = sum / 10;
          int digit = sum % 10;
          
          
          temp.next = new ListNode(digit);
          temp = temp.next;
          
              if (temp1 != null) temp1 = temp1.next;
              if (temp2 != null) temp2 = temp2.next;
        }
        return dummy.next;
    }
}