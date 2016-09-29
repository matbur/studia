import java.util.ArrayList;

public class Natural {
    protected ArrayList<Integer> vec;

    Natural() {
        this.vec = new ArrayList<>();
        this.vec.add(0);
    }

    Natural(String arg) {
        this.vec = new ArrayList<>();
        char[] tab = arg.toCharArray();

        for (char i: tab) {
            if (Character.isDigit(i)) {
                vec.add(0, Character.getNumericValue(i));
            }
        }
    }

    Natural(Natural arg) {
        this.vec = arg.vec;
    }


    public Natural add(Natural arg) {
        Natural res = new Natural();

        while (res.size() <= this.size() || res.size() <= arg.size())
            res.vec.add(0);
        res.vec.add(0);

        for (int i = 0; i < res.size(); ++i) {
            if (i < this.size())
                res.vec.set(i, res.vec.get(i) + this.vec.get(i));
            if (i < arg.size())
                res.vec.set(i, res.vec.get(i) + arg.vec.get(i));
            if (res.vec.get(i) > 9) {
                res.vec.set(i, res.vec.get(i) - 10);
                res.vec.set(i+1, res.vec.get(i+1) + 1);
            }
        }

        while (res.vec.get(res.size()-1) == 0 && res.size() > 1)
            res.vec.remove(res.size()-1);

        return res;
    }

    public Natural mul(Natural arg) {
        Natural res = new Natural();

        while (res.size() <= this.size() +  arg.size())
            res.vec.add(0);
        res.vec.add(0);

        for (int i = 0; i < this.size(); ++i) {
            for (int j = 0; j < arg.size(); ++j) {
                int k = i + j;
                res.vec.set(k, res.vec.get(k) + this.vec.get(i) * arg.vec.get(j));
                while (res.vec.get(k) > 9) {
                    res.vec.set(k, res.vec.get(k) - 10);
                    res.vec.set(k+1, res.vec.get(k+1) + 1);
                }
            }
        }

        while (res.vec.get(res.size()-1) == 0 && res.size() > 1)
            res.vec.remove(res.size()-1);

        return res;
    }

    public Natural div(Natural arg) {
        Natural res = new Natural("1");
        Natural prev = new Natural();

        if (arg.gt(this))
            return new Natural();

        for (int i = 2000000000; i > 1; i /= 2) {
            while (this.ge(arg.mul(res))) {
                prev = res;
                res = res.mul(new Natural(Integer.toString(i)));
            }
            res = prev;
        }
        
        String n = "1";
        for (int i = 0; i < this.size(); ++i)
            n = n + "1";

        for (Natural i = new Natural(n); i.size() > 0; i.vec.remove(0)) {
            while (this.ge(arg.mul(res))) {
                prev = res;
                res = res.add(i);
            }
            res = prev;
        }

        return res;
    }

    protected boolean eq(Natural arg) {
        if (this.size() != arg.size())
            return false;

        for (int i = 0; i < this.size(); ++i)
            if (this.vec.get(i) != arg.vec.get(i))
                return false;

        return true;
    }

    protected boolean gt(Natural arg) {
        if (this.size() > arg.size())
            return true;
        if (this.size() < arg.size())
            return false;

        for (int i = this.size() - 1; i >= 0; --i)
            if (this.vec.get(i) == arg.vec.get(i))
                continue;
            else
                return this.vec.get(i) > arg.vec.get(i);

        return false;
    }

    protected boolean ge(Natural arg) {
        return this.gt(arg) || this.eq(arg);
    }


    public String toString() {
        String result = new String();

        for (int i: vec) {
            result = Integer.toString(i)+result;
        }

        return result;
    }
    
    public int size() { return vec.size(); }
}
