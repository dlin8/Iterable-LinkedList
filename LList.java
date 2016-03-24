/* Derek Lin
   APCS2 pd5
   HW16 -- Generational Improvement
   2016-03-21 */

public class LList<T> implements List<T>{
    // Attributes
    private DLLNode _head;
    private DLLNode _tail;
    private int _size;

    // Constructor
    public LList(){
	DLLNode<T> end = new DLLNode(null, null, null);
	DLLNode<T> start = new DLLNode(null, null, end);
	end.setPrev( start );
	_head = start;
	_tail = end;
	_size = 0;
    }

    // Implements
    public boolean add( T x ){
	DLLNode<T> node = new DLLNode(x, _tail.getPrev(), _tail);
	_tail.getPrev().setNext( node );
	_tail.setPrev( node );
	_size++;
	return true;
    }
    public void add( int i, T x ){
	DLLNode<T> prev = _head;
	for(int c = 0; c < i; c++){
	    prev = prev.getNext();
	}
	DLLNode<T> node = new DLLNode( x, prev, prev.getNext() );
	prev.setNext( node );
	node.getNext().setPrev( node );
	_size++;
    }
    public T remove( int i ){
	DLLNode<T> prev = _head;
	for(int c = 0; c < i; c++){
	    prev = prev.getNext();
	}
	DLLNode<T> cya = prev.getNext();
	prev.setNext( (prev.getNext()).getNext() );
	_size--;
	return cya.getGuts();
    }
    public T get( int i ){
	DLLNode<T> current = _head;
	for(int x = -1; x < i; x++){
	    current = current.getNext();
	}
	return current.getGuts();
    }
    public T set( int i, T x ){
	DLLNode<T> current = _head;
	for(int c = -1; c < i; c++){
	    current = current.getNext();
	}
	T old = current.getGuts();
	current.setGuts( x );
	return old;
    }
    public int size(){
	return _size;
    }

    // toString() // CAR + CDR
    public String toString(){
	String retStr = "";
	for(int i = 0; i < size(); i++){
	    retStr += get( i ) + ", ";
	}
	return retStr.substring(0, retStr.length() - 2);
    }

    class Iterator implements Iterator{
	DLLNode<T> _current;
	boolean _safe;

	Iterator(){
	    _current = _head;
	    _safe = false;
	}

	boolean hasNext(){
	    if( ! _current.getNext().equals(null) ){
		return false;
	    }
	    return true;
	}

	Object next(){
	    if( this.hasNext() ){
		DLLNode temp = _current;
		_current = _current.getNext();
		_safe = true;
		return temp.getNext();
	    }
	}

	void remove(){
	    if( _safe ){
		_safe = false;
	    }
	    else{
		throw new IllegalStateException();
	    }
	}
    }

    // Tester
    public static void main(String[] args){
	LList Link = new LList();

	Link.add( "Chicken" );
	Link.add( "French Fries" );
	Link.add( "Salad" );
	Link.add( "Hamburgers" );
	Link.add( "Pizza" );
	Link.add( 4 );
	Link.add( 23.22 );
	System.out.println( Link.size() );
	System.out.println( Link );
	System.out.println();
	
	System.out.println( Link.get(2) );
	Link.set( 2, "Hot Dogs" );
	System.out.println( Link );
	System.out.println();

	Link.add( 0, "A Really Healthy Salad" );
	System.out.println( Link.size() );
	System.out.println( Link );
	System.out.println();
	
	Link.remove( 0 );
	System.out.println( Link.size() );
	System.out.println( Link );
	System.out.println();

	/*=================================
	*/
    }
}
