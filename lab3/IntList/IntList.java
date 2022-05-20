import java.util.Formatter;

public class IntList {

    /** First element of list. */
    private int first;
    /** Remaining elements of list. */
    private IntList rest;

    /** A List with first and rest. */
    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }

    /** A List with null rest, and first = 0. */
    public IntList() {
        this(0, null);
    }
    
    /** Returns a list equal to L with all elements squared. Destructive. */
    public static void dSquareList(IntList L) {
        
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }
    
    /** Returns a list equal to L with all elements squared. Non-destructive. */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }

        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;

        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }

        return res;
    }

    /** Returns a list equal to L with all elements squared. Non-destructive. */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListIterative(L.rest));
    }


    /** Returns a list consisting of the elements of A followed by the
     *  elements of B. May modify items of A. Don't use 'new'.
     *  */
    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null && B != null) {
            return B;
        } else if (B == null && A != null) {
            return A;
        } else if (A == null && B == null) {
            return null;
        }

        IntList p = A;
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = B;

        return A;
    }


    /** Returns a list consisting of the elements of A followed by the
     * * elements of B. May NOT modify items of A. Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        if (A == null && B != null) {
            return B;
        } else if (A != null && B == null) {
            return A;
        } else if (A == null && B == null) {
            return null;
        }

        return new IntList(A.first, catenate(A.rest, B));
    }


    /** Returns a new IntList containing the ints int ARGS. */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k++, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }

        return result;
    }


    /**
     * Returns true if X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }

        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }

        if (p != null || L != null) {
            return false;
        }

        return true;
    }


    /**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null
     * as an input, returns null.
     */
    public static IntList reverse(IntList A) {
        if (A == null) {
            return null;
        }

        /** Use 'new' Create List, Return result list.
        IntList res = new IntList(A.first, null);
        IntList p = A.rest;

        while (p != null) {
            res = new IntList(p.first, res);
            p = p.rest;
        }

        return res;
        */

        IntList p = A, q = A.rest, t;
        p.rest = null;

        while (q != null) {
            t = q.rest;
            q.rest = p;
            p = q;
            q = t;
        }

        return p;
    }


    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}