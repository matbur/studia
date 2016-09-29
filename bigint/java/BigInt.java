import java.util.ArrayList;

public class BigInt extends Natural {
    private boolean sign;

    BigInt() {
        super();
        this.sign = true;
    }

    BigInt(String arg) {
        super(arg);
        this.sign = arg.charAt(0) != '-';
    }

    BigInt(Natural arg) {
        super(arg);
        this.sign = true;
    }

    BigInt(BigInt arg) {
        super(arg);
        this.sign = arg.sign;
    }


    public Natural abs() {
        BigInt res = new BigInt(this);
        res.sign = true;
        return res;
    }

    public BigInt min() {
        BigInt res = new BigInt(this);
        res.sign = !this.sign;
        return res;
    }

    private BigInt add_same_sign(BigInt arg) {
        Natural nt = new Natural(this);
        Natural na = new Natural(arg);

        BigInt res = new BigInt(na.add(nt));
        res.sign = this.sign;

        return res;
    }

    private BigInt add_diff_sign(BigInt arg) {
        if (this.vec == arg.vec)
            return new BigInt();

        BigInt res = new BigInt();

        int agtaa;
        if (this.abs().gt(arg.abs()))
            agtaa = 1;
        else 
            agtaa = -1;

        while (res.size() <= this.size() || res.size() <= arg.size())
            res.vec.add(0);
        res.vec.add(0);

        for (int i = 0; i < res.size(); ++i) {
            if (i < this.size())
                res.vec.set(i, res.vec.get(i) + this.vec.get(i) * agtaa);

            if (i < arg.size())
                res.vec.set(i, res.vec.get(i) - arg.vec.get(i) * agtaa);

            if (res.vec.get(i) < 0) {
                res.vec.set(i, res.vec.get(i) + 10);
                res.vec.set(i+1, res.vec.get(i+1) - 1);
            }
        }

        if (agtaa == 1)
            res.sign = this.sign;
        else
            res.sign = arg.sign;

        while (res.vec.get(res.size()-1) == 0 && res.size() > 1)
            res.vec.remove(res.size()-1);

        return res;
    }

    public BigInt add(BigInt arg) {
        if (this.sign == arg.sign)
            return this.add_same_sign(arg);
        else
            return this.add_diff_sign(arg);
    }

    public BigInt sub(BigInt arg) {
        return this.add(arg.min());
    }

    public BigInt mul(BigInt arg) {
        Natural nt = new Natural(this);
        Natural na = new Natural(arg);

        BigInt res = new BigInt(na.mul(nt));
        res.sign = (this.sign == arg.sign);

        return res;
    }

    public BigInt div(BigInt arg) {
        Natural nt = new Natural(this);
        Natural na = new Natural(arg);

        BigInt res = new BigInt(nt.div(na));
        res.sign = (this.sign == arg.sign);

        return res;
    }


    private boolean gt(BigInt arg) {
        if (this.sign)
            if (!arg.sign)
                return true;
            else
                return this.abs().gt(arg.abs());
        else if (arg.sign)
            return false;
        else
            return arg.abs().gt(this.abs());
        
    }

    public String toString() {
        String result = super.toString();

        if (!sign)
            result = "-" + result;

        return result;
    }

    public boolean get_sign() { return this.sign; }
}
