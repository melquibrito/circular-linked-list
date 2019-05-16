package circularlinkedlist;

public class CircularLinkedList<T extends Comparable> {

    private class Element {

        Element(T object, Element next) {
            this.object = object;
            this.next = next;
        }

        Element() {
        }

        T object;
        Element next;

    }

    private Element first = null;

    public int size() {
        int size = 0;
        if (!isEmpty()) {
            Element node = first;
            size = 1;
            while (node.next != first) {
                size++;
                node = node.next;
            }
        }
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void add(T object) {
        Element node = new Element();
        if (isEmpty()) {
            first = node;
        } else {
            Element aux = first;
            while (aux.next != first) {
                aux = aux.next;
            }
            aux.next = node;
        }
        node.object = object;
        node.next = first;
    }

    public boolean remove(T object) {
        if (isEmpty()) {
            return false;
        }
        Element previous = null, node = first;
        int i = 0;
        while (i < this.size()) {
            if (node != null && node.object.equals(object)) {
                break;
            }
            previous = node;
            node = node.next;
            i++;
        }
        if (node.object.equals(object)) {
            if (node == first) {
                if (this.size() > 1) {
                    getElement(this.size() - 1).next = first.next;
                    first = first.next;
                } else {
                    first = null;
                }
            } else {
                if (previous != null) {
                    previous.next = node.next;
                }
            }
            return true;
        }
        return false;
    }

    public T get(int index) {
        if (getElement(index) != null) {
            return getElement(index).object;
        }
        return null;
    }

    private Element getElement(int index) {
        if (!isEmpty() && index >= 0) {
            if (index == 0) {
                return first;
            } else {
                Element node = first;
                for (int i = 1; i < this.size(); i++) {
                    node = node.next;
                    if (i == index) {
                        return node;
                    }
                }
            }
        }
        return null;
    }

    public boolean remove(int index) {
        if (index > -1 && index < this.size()) {
            if (index == 0) {
                if (this.size() > 1) {
                    getElement(this.size() - 1).next = first.next;
                    first = first.next;
                } else {
                    first = null;
                }
            } else {
                Element previous = this.getElement(index - 1), node = this.getElement(index);
                previous.next = node.next;
            }
            return true;
        }
        return false;
    }

    public synchronized void addNeatly(T object) {
        add(object);
        sort();
    }

    public void sort() {
        if (!isEmpty() && size() > 1) {
            Element comesFirst = first;
            for (int i = 1; i < this.size(); i++) {
                if (comesFirst.object.compareTo(get(i)) > 0) {
                    comesFirst = getElement(i);
                }
            }
            
            Element previous, last = getElement(size() - 1), next = comesFirst.next;
            if(last == comesFirst) {
                last = getElement(size() - 2);
            }
            if (comesFirst != first) {
                previous = getElement(indexOf(comesFirst.object) - 1);
                comesFirst.next = first;
                if(next != first) {
                    previous.next = next;
                }else{
                    previous.next = comesFirst;
                }
                first = comesFirst;
                last.next = first;
            }
            
            int startingPoint = 2;
            for (int i = 1; i < this.size(); i++) {
                comesFirst = getElement(i);
                
                for (int j = startingPoint; j < this.size(); j++) {
                    if (comesFirst.object.compareTo(get(j)) > 0) {
                        comesFirst = getElement(j);
                    }
                }
                if (comesFirst != getElement(i)) {
                    next = comesFirst.next;
                    previous = getPrevious(comesFirst);
                    Element previous_i = getElement(i - 1);
                    comesFirst.next = getElement(i);
                    previous.next = next;
                    previous_i.next = comesFirst;
                }
                startingPoint++;

            }
        }
    }

    private Element getPrevious(Element element) {
        Element previous = null, node = first;
        if (!isEmpty() && element != first) {
            while (node.next != first && node != element) {
                previous = node;
                node = node.next;
            }
        }
        return previous;
    }

    public int indexOf(T object) {
        if (!isEmpty()) {
            Element node = first;
            int i = 0;
            do {
                if (node.object.equals(object) || node.object.compareTo(object) == 0) {
                    return i;
                }
                if(node.next != first) {
                    node = node.next;
                }
                i++;
            } while (node != first);
        }
        return -1;
    }

    public boolean contains(T object) {
        return indexOf(object) > -1;
    }

    public void set(int index, T object) {
        if (index >= 0) {

            if (index < size()) {
                Element added = new Element(object, null);
                if (index == 0) {
                    added.next = first.next;
                    getElement(this.size() - 1).next = added;
                    first = added;
                    
                } else {
                    added.next = getElement(index).next;
                    getElement(index - 1).next = added;
                }
            } else {
                add(object);
            }
        }
    }

    public void addPreventingRepetition(T object) {
        if (!contains(object)) {
            add(object);
        }
    }

    @Override
    public String toString() {
        String list = "[]";
        if (!isEmpty()) {
            list = "[" + first.object;
            if (this.size() > 1) {
                for (int i = 1; i < this.size(); i++) {
                    list = list + ", " + getElement(i).object;
                }
            }
            list = list + "]";
        }
        return list;
    }

}
