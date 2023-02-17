package mockitoStudy;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
public class Demo3 {
    private class TestClass{
        public String get(String a) {
            return "";
        }
    }

    //Stubbing with callbacks
    @Test
    public void demo01() {
        TestClass mock = mock(TestClass.class);
        when(mock.get(anyString())).thenAnswer(
                (Answer) invocation -> {
                    Object[] args = invocation.getArguments();
                    Object mock1 = invocation.getMock();
                    return "called with arguments: " + Arrays.toString(args);
                });

        //Following prints "called with arguments: [foo]"
        System.out.println(mock.get("foo"));
    }

    //doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod() family of methods
    @Test
    public void demo02() {
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();
    }

    //Spying on real objects
    @Test
    public void demo03() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");

        List list2 = new LinkedList();
        List spy2 = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        when(spy2.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy2).get(0);
    }
    private class Foo{
        String someMethod(){return "";}
        String someMethod2(String s){return "test";}

    }
    //Real partial mocks (Since 1.8.0)
    @Test
    public void demo04() {
        //you can create partial mock with spy() method:
        List list = spy(new LinkedList());

        //you can enable partial mock capabilities selectively on mocks:
        Foo mock = mock(Foo.class);
        //Be sure the real implementation is 'safe'.
        //If real implementation throws exceptions or depends on specific state of the object then you're in trouble.
        when(mock.someMethod()).thenCallRealMethod();
    }

    private class TestClass2{
        String test(Foo foo){return "";}
    }
    //Capturing arguments for further assertions (Since 1.8.0)
    @Test
    public void demo05(){
        TestClass2 mock = mock(TestClass2.class);
        ArgumentCaptor<Foo> argument = ArgumentCaptor.forClass(Foo.class);
        mock.test(new Foo());
        verify(mock).test(argument.capture());
        assertEquals("test", argument.getValue().someMethod2(""));
    }

}
