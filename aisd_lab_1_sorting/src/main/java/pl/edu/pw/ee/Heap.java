package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap implements HeapInterface<Double> {
    private double[] nodes = null;

    public Heap() {
        nodes = new double[1000];
    }
    
    @Override
    public void put(Double item) {
        if(nodes == null) {
            
        }
    }

    @Override
    public Double pop() {
        
        
        return null;
    }

    

}
