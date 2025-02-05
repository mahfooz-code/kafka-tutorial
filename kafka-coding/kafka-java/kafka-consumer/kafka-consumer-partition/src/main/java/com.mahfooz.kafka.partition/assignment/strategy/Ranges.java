/*
Assigns to each consumer a consecutive subset of partitions from each topic it subscribes to.
So if consumers C1 and C2 are subscribed to two topics, T1 and T2, and each of the topics has three partitions, 
then C1 will be assigned partitions 0 and 1 from topics T1 and T2, while C2 will be assigned partition 2 from 
those topics.
Because each topic has an uneven number of partitions and the com.mahfooz.kafka.partition.assignment is done for each topic independently,
the first consumer ends up with more partitions than the second.
This happens whenever Range com.mahfooz.kafka.partition.assignment is used and the number of consumers does not divide the number of
partitions in each topic neatly.

 */

package com.mahfooz.kafka.partition.assignment.strategy;

/**
 *
 * @author mahfooz
 */
public class Ranges {
    public static void main(String [] args) {
    
    }
}
