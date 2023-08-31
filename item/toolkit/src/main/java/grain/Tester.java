package grain;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Tester {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<P> queue = new DelayQueue<>();
        P p = new P();
        p.setInner(100L);
        System.out.println(System.currentTimeMillis());
        queue.offer(p, 10L, TimeUnit.SECONDS);
        P take = queue.take();
        System.out.println(System.currentTimeMillis());

    }

    private static class P implements Delayed {
        long inner;

        public long getInner() {
            return inner;
        }

        public void setInner(long inner) {
            this.inner = inner;
        }

        /**
         * Returns the remaining delay associated with this object, in the
         * given time unit.
         *
         * @param unit the time unit
         * @return the remaining delay; zero or negative values indicate
         * that the delay has already elapsed
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.toSeconds(inner);
        }

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         *
         * <p>The implementor must ensure {@link Integer#signum
         * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
         * all {@code x} and {@code y}.  (This implies that {@code
         * x.compareTo(y)} must throw an exception if and only if {@code
         * y.compareTo(x)} throws an exception.)
         *
         * <p>The implementor must also ensure that the relation is transitive:
         * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
         * {@code x.compareTo(z) > 0}.
         *
         * <p>Finally, the implementor must ensure that {@code
         * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
         * == signum(y.compareTo(z))}, for all {@code z}.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
        //         * @apiNote It is strongly recommended, but <i>not</i> strictly required that
         * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
         * class that implements the {@code Comparable} interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.inner - o.getDelay(TimeUnit.SECONDS));
        }
    }
}
