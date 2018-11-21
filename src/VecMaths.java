import java.util.Arrays;

public class VecMaths {
    //return the length/magnitude of a vector
    static float length(float[] vec) {
        float mag = 0;
        for(int i=0; i< vec.length;i++) {
            mag += vec[i] * vec[i];
        }
        return (float)Math.sqrt(mag);//Pythagorous Theorem
    }
    //make all value is less length but great than zero
    public static void clamp(float[] vec, float length) {
        for(int i=0; i< vec.length;i++) {
            vec[i] = Math.max(0,Math.min(vec[i], length));
        }
    }
    //adding 2 vector is x + x' and y + y'
    public static float[] add(float result[],float[] vecA, float[] vecB) {
        for(int i=0; i< vecA.length;i++) {
            result[i] = vecA[i] + vecB[i];
        }
        return result;
    }
    //subtracting 2 vector is x - x' and y - y'
    public static float[] subtract(float result[],float[] vecA, float[] vecB) {
        for(int i=0; i< vecA.length;i++) {
            result[i] = vecA[i] - vecB[i];
        }
        return result;
    }
    //Scalar Multiply
    //Scalar Multiply vector is A* x' and A* y'
    public static float[] scalarMulti(
            float result[],float scalar, float[] vec) {
        for(int i=0; i< vec.length;i++) {
            result[i] = vec[i] * scalar;
        }
        return result;
    }
    //Distance between 2 vectors
    public static float dist(float[] vecA, float[] vecB) {
        float result[] = new float[2];
        result = subtract(result,vecA,vecB);
        return length(result);
    }

    public static void main(String args[]) {
        float[] vec = {3 , 4};
        float[] vecA= {5 , 6};
        float[] vecB= {7 , 8};
        float[] result = new float[2];
        //length
        System.out.println("length of " + Arrays.toString(vec) + " is " +
                length(vec));
        //clamp
        clamp(vec,2);
        System.out.println("Clamp: " + Arrays.toString(vec));

        //add
        add(result, vecA, vecB);
        System.out.println("Add: " + Arrays.toString(result));

        //subtract
        subtract(result, vecA, vecB);
        System.out.println("Subtract: " + Arrays.toString(result));

        //scalar Multiply
        scalarMulti(result, 4, vec);
        System.out.println("ScalarMulti: " + Arrays.toString(result));

        //dist
        System.out.println("Distance of "+ dist(vecA, vecB));
    }



}