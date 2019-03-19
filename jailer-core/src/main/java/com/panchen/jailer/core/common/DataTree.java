package com.panchen.jailer.core.common;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**                     jailer
 *                        |  
 *                     region1
 *                   /    |   \
 *                 pro   test  dev
 *             /    |   \
 *           user order commodity 
 *        /   |   \ 
 *     user1IP user2IP .....    
 *     
 *     Consider the above standard format , Unconstrained must be so multi-layered , But at least jailer-user-user1IP
 *     
 * @author pc
 *
 */
public class DataTree {

    private static String NODEHEADKEY = "jailer";
    private static String CUTTERCHAR = "-";

    private Node head = new Node(NODEHEADKEY);
    
    private AtomicLong nodeNum=new AtomicLong(0);
    
    public Map<String, Set<Node>> getAllNamingNode(){
        return null;
        
    }
    
    public Set<Node> get(String key) {
        String[] levelData=key.split(CUTTERCHAR);
        vaild(levelData);
        return widthGet(levelData);
    }
    
    private Set<Node> widthGet(String[] data) {
        Node tmp=head;
        for(int i=0;i<data.length-1;i++) {
            for(Node child:tmp.childs) {
                if(data[i].equals(child.value)) {
                    tmp=child;
                    break;
                }
                
            }
        }
        return tmp.childs;
    }
    
    
    
    public void put(String data) {
        String[] levelData=data.split(CUTTERCHAR);
        vaild(levelData);
        Node tmp=head;
        depethGetAndPut(levelData.length,levelData,tmp,0);
    }
    
    
    
    
    private void depethGetAndPut(int level,String[] data,Node tmp,int frequency) {
        tmp.childLock.writeLock().lock();
        try {
            for(Node child:tmp.childs) {
                if(child.value.equals(data[frequency])) {
                    tmp.childLock.writeLock().unlock();
                    tmp=child;
                    if(0==--level) {
                        tmp.childs.add(new Node(data[frequency]));
                        return;
                    }
                    depethGetAndPut(level,data,tmp,frequency++);
                    return;
                    
                }
             }
            appendLeaf(tmp,data,frequency);
        } catch (Exception e) {
            //todo
        }finally {
            tmp.childLock.writeLock().unlock();
        }


           
    }
    
    
    private void appendLeaf(Node tmp,String[] data,int index) {
        for(;index<data.length;index++) {
            Node n=new Node(data[index]);
            tmp.childs.add(n);
            tmp=n;
        }
    }
    
    private boolean vaild(String[] data) {
        if (null == data || 1 >= data.length || !NODEHEADKEY.equals(data[0])) {
            return false;
        }
        return true;
    }

    public class Node {

        Node(String value) {
            this.value = value;
        }

        public String value;
        public Set<Node> childs=new HashSet<>();
        public long lastCallTime;
        
        private ReadWriteLock childLock=new ReentrantReadWriteLock();
        

    }



}
