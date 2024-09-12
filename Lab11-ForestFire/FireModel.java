public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;
    private boolean check;

    public FireModel(FireView view)
    {
        check = false;
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

   public void ignite(int r, int c) {
      if(myGrid[r][c].getStatus()==1) {
         myGrid[r][c].setStatus(2);
      } else {
         return;
      }
      if (r+1 < myGrid.length) {
         ignite(r+1,c);
      }
      if (r-1 >= 0) {
         ignite(r-1,c);
      } else {
         check = true;
      }
      if (c+1 < myGrid.length) {
         ignite(r,c+1);
      }
      if (c-1 >= 0) {
         ignite(r,c-1);
      }
   }

    public void solve()
    {
        for (int i = 0; i < myGrid[0].length; i++) {
            ignite(myGrid.length-1, i);
        }
        if (check == true) {
            
            System.out.print("You did not survive. Get gud");
        } else {
            System.out.print("You survived!");
        }
        myView.updateView(myGrid);
    }

}
