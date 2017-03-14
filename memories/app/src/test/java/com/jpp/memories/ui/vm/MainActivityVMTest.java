package com.jpp.memories.ui.vm;

import com.jpp.memories.core.MemoriesManager;
import com.jpp.memories.core.Navigator;
import com.jpp.memories.model.Memory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainActivityVMTest {

    @Mock
    MemoriesManager memoriesManager;
    @Mock
    Navigator navigator;

    @InjectMocks
    MainActivityVM mainActivityVM;

    @Before
    public void setUp() throws Exception {
        this.mainActivityVM = new MainActivityVM(navigator, memoriesManager);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insertMemoryListTest() throws Exception {
        Memory memory = new Memory("abc", "def");
        this.mainActivityVM.addMemory(memory);
        assertEquals(this.mainActivityVM.getMemoriesList().isEmpty(), false);
        assertEquals(this.mainActivityVM.getMemoriesList().get(0).getQuote(), memory.getQuote());
    }

    @Test
    public void insertLocalMemoriesListTest() throws Exception {
        Memory memory = new Memory("abc", "def");
        when(this.memoriesManager.getMemories()).thenReturn(Arrays.asList(memory));
        this.mainActivityVM.insertLocalMemories();
        assertEquals(this.mainActivityVM.getMemoriesList().isEmpty(), false);
        assertEquals(this.mainActivityVM.getMemoriesList().get(0).getQuote(), memory.getQuote());
    }

    @Test
    public void insertNullMemoryInListTest() throws Exception {
        this.mainActivityVM.addMemory(null);
        assertEquals(this.mainActivityVM.getMemoriesList().isEmpty(), true);
    }

}