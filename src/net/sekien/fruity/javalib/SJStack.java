package net.sekien.fruity.javalib;

import net.sekien.fruity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA. User: matt Date: 4/10/13 Time: 4:24 PM To change this template use File | Settings | File
 * Templates.
 */
public class SJStack implements SJInterface {
	@Override public void register(SRootClosure root) {
		root.bind("dup", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				stack.push(stack.peek());
			}
		}));
		root.bind("scopy", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				stack.push(stack.peek().shallowCopy());
			}
		}));
		root.bind("dcopy", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				stack.push(stack.peek().deepCopy());
			}
		}));
		root.bind("pop", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				stack.pop();
			}
		}));
		root.bind("swap", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				SObject b = stack.pop();
				SObject a = stack.pop();
				stack.push(b);
				stack.push(a);
			}
		}));
		root.bind("stksize", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				stack.push(new SInteger(stack.size()));
			}
		}));
		root.bind("dbstk", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				System.out.println("bdstk: "+stack);
			}
		}));
		root.bind("reverse", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				List<SObject> reverse_stack = new ArrayList<SObject>(stack.size());
				for (SObject obj : stack) {
					reverse_stack.add(0, obj);
				}
				stack.clear();
				stack.addAll(reverse_stack);
			}
		}));
		root.bind("sendback", new SJavaClosure(root, new JavaFunction() {
			@Override public void onCall(Stack<SClosure> callStack, Stack<SObject> stack, SClosure parent) {
				stack.insertElementAt(stack.pop(), 0);
			}
		}));
	}
}
