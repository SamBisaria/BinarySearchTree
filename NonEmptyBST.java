package assn04;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _parent;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	@Override
	public BST<T> insert(T element) {
		if(getElement().compareTo(element) < 0){
			if(_right.isEmpty()){
				_right = new NonEmptyBST<T>(element);
				((NonEmptyBST<T>) _right)._parent = this;
			}
			else{
				_right.insert(element);
			}
		}
		else if (getElement().compareTo(element) > 0){
			if(_left.isEmpty()){
				_left = new NonEmptyBST<T>(element);
				((NonEmptyBST<T>) _left)._parent = this;
			}
			else{
				_left.insert(element);
			}
		}
		return(this);
	}

	@Override
	public BST<T> remove(T element) {
		if (getElement() == element) {
			if (_parent == null) {
				return (new EmptyBST<T>());
			}
			if (getElement().compareTo(_parent.getLeft().getElement()) == 0){
				if (_left.isEmpty() && !_right.isEmpty()){
					((NonEmptyBST) _right)._parent = _parent;
					((NonEmptyBST) _parent)._left = _right;
					_element = _right.getElement();
					return(this);
				}
				else if (!_left.isEmpty() && _right.isEmpty()){
					((NonEmptyBST) _left)._parent = _parent;
					((NonEmptyBST) _parent)._left = _left;
					_element = _left.getElement();
					return(this);
				}
				else if (_left.isEmpty() && _right.isEmpty()){
					((NonEmptyBST) _parent)._left = new EmptyBST<T>();
					_element = null;
					return(this);
				}
				else if (!_left.isEmpty() && !_right.isEmpty()){
					((NonEmptyBST<T>) _parent)._left = _right;
					((NonEmptyBST<T>) _right)._parent = _parent;
					_element = _right.getElement();
					_right.insert(_left.getElement());
					return(new EmptyBST<>());
				}
			}
			else{
				if (_left.isEmpty() && !_right.isEmpty()){
					((NonEmptyBST) _right)._parent = _parent;
					((NonEmptyBST) _parent)._right = _right;
					_element = _right.getElement();
					return(this);
				}
				else if (!_left.isEmpty() && _right.isEmpty()){
					((NonEmptyBST) _left)._parent = _parent;
					((NonEmptyBST) _parent)._right = _left;
					_element = _left.getElement();
					return(this);
				}
				else if (_left.isEmpty() && _right.isEmpty()){
					((NonEmptyBST) _parent)._right = new EmptyBST<T>();
					return(new EmptyBST<>());
				}
				else if (!_left.isEmpty() && !_right.isEmpty()){
					((NonEmptyBST<T>) _parent)._right = _left;
					((NonEmptyBST<T>) _left)._parent = _parent;
					_element = _left.getElement();
					_right.insert(_element);
					return(new EmptyBST<>());
				}
			}
		}
		if (getElement().compareTo(element) > 0) {
			getLeft().remove(element);
		}
		else if (getElement().compareTo(element) < 0) {
			getRight().remove(element);
		}
		return(this);
	}

	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element + " ");
		if (!_left.isEmpty()) {
			_left.printPreOrderTraversal();
		}
		if (!_right.isEmpty()) {
			_right.printPreOrderTraversal();
		}
	}

	@Override
	public void printPostOrderTraversal() {
		if (!_left.isEmpty()) {
			_left.printPostOrderTraversal();
		}
		if (!_right.isEmpty()) {
			_right.printPostOrderTraversal();
		}
		System.out.print(_element + " ");
	}

	@Override
	public void printBreadthFirstTraversal() {
		Queue<BST<T>> queue = new LinkedList<BST<T>>();
		queue.add(this);
		while(!queue.isEmpty()){
			BST<T> current = queue.remove();
			if(!current.getLeft().isEmpty()) queue.add(current.getLeft());
			if(!current.getRight().isEmpty()) queue.add(current.getRight());
			System.out.print(current.getElement() + " ");
		}
	}

	// GetHeight of A Tree

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
