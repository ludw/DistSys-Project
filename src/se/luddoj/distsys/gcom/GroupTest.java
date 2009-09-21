package se.luddoj.distsys.gcom;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class GroupTest {
	public Group g;
	
	@Before
	public void setUp() {
		g = new Group();
	}
	
	@Test
	public void testCreateObject() {
		assertNotNull(g);
	}
	
	@Test
	public void testAddMember() {
		Member member = new Member();
		assertEquals(0, g.listMembers().size());
		g.addMember(member);
		assertEquals(1, g.listMembers().size());
		assertEquals(member, g.listMembers().get(0));
	}

	@Test
	public void testRemoveMember() {
		Member member = new Member();
		assertEquals(0, g.listMembers().size());
		g.addMember(member);
		assertEquals(1, g.listMembers().size());
		g.removeMember(member);
		assertEquals(0, g.listMembers().size());
	}

	@Test
	public void testListMembers() {
		Member member1 = new Member();
		Member member2 = new Member();
		Member member3 = new Member();
		assertEquals(0, g.listMembers().size());
		g.addMember(member1);
		g.addMember(member2);
		g.addMember(member3);
		assertEquals(member1, g.listMembers().get(0));
		assertEquals(member2, g.listMembers().get(1));
		assertEquals(member3, g.listMembers().get(2));
	}

}
