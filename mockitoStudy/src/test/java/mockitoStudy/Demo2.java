package mockitoStudy;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
public class Demo2 {

    //Stubbing void methods with exceptions
    @Test
    public void demo01() {
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();
    }

    //Verification in order
    @Test
    public void demo02() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first", then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder2 = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder2.verify(firstMock).add("was called first");
        inOrder2.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }

    //Making sure interaction(s) never happened on mock
    @Test
    public void demo03() {
        LinkedList mockOne = mock(LinkedList.class);
        LinkedList mockTwo = mock(LinkedList.class);
        LinkedList mockThree = mock(LinkedList.class);

        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyZeroInteractions(mockTwo, mockThree);
    }

    //Finding redundant invocations
    @Test
    public void demo04() {
        LinkedList mockedList = mock(LinkedList.class);

        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
        verifyNoMoreInteractions(mockedList);
    }

    //Stubbing consecutive calls (iterator-style stubbing)
    @Test
    public void demo05(){
        LinkedList mock = mock(LinkedList.class);
        when(mock.add("some arg"))
                .thenThrow(new RuntimeException())
                .thenReturn( true , true, false );

        //First call: throws runtime exception:
        try {
            mock.add("some arg");
        }catch (Exception ignored){

        }

        //Second call: prints "true"
        System.out.println(mock.add("some arg"));

        //Third call: prints "true"
        System.out.println(mock.add("some arg"));

        //Forth call: prints "false"
        System.out.println(mock.add("some arg"));

        //Any consecutive call: prints "false" as well (last stubbing wins).
        System.out.println(mock.add("some arg"));
    }

}
