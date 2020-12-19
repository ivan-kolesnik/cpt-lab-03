package com.lab_03.signal;

public class Signal {
    private byte[] _data;

    public Signal(byte[] data) throws IllegalArgumentException {
        if (data.length == 0) {
            throw new IllegalStateException();
        }

        _data = data;
    }

    public byte getMinValue() {
        byte min = _data[0];
        for (int i = 1; i < _data.length; i += 1) {
            if (_data[i] < min) {
                min = _data[i];
            }
        }

        return min;
    }

    public byte getMaxValue() {
        byte max = _data[0];
        for (int i = 1; i < _data.length; i += 1) {
            if (_data[i] > max) {
                max = _data[i];
            }
        }

        return max;
    }

    public double getAverageValue() {
        double acc = 0;
        for (byte v: _data) {
            acc += v;
        }

        return acc / _data.length;
    }

    public byte getDynamicRange() {
        byte max = getMaxValue();
        byte min = getMinValue();

        return (byte) (max - min);
    }

    public double getEnergy() {
        double acc = 0;
        for (byte v: _data) {
            acc += Math.pow(v, 2);
        }

        return acc;
    }

    public double getAveragePower() {
        double energy = getEnergy();
        return energy / _data.length;
    }

    public double getDispersion() {
        double average = getAverageValue();
        double acc = 0;

        for (byte v: _data) {
            acc += Math.pow((v - average), 2);
        }

        return acc / _data.length;
    }

    public double getAutocorrelation(int tau) {
        if (tau < 0) {
            return getAutocorrelation(-tau);
        }

        double average = getAverageValue();
        double acc = 0;

        for (int i = 0; i < _data.length - tau; i += 1) {
            acc += ((_data[i + tau] - average) * (_data[i] - average));
        }

        return acc / (_data.length - tau);
    }

    public double getCorrelationInterval() {
        double acc = 0;
        for (int i = 0; i < _data.length; i += 1) {
            acc += getAutocorrelation(i);
        }

        return acc / getAutocorrelation(0);
    }
}
