package kt.MD3.empty

import org.junit.Test
import kotlin.math.sqrt

typealias Set = (Int) -> Boolean

infix fun Set.contains(x: Int): Boolean = this.forAll { it == x }

fun singletonSet(x: Int): Set = {it == x}

infix fun Set.union(set: Set): Set = {contains(it) || set.contains(it)}

infix fun Set.intersect(set: Set): Set = {contains(it) && set.contains(it)}

infix fun Set.diff(set: Set): Set = {contains(it) && set.contains(it).not()}

fun Set.filter(predicate: (Int) -> Boolean): Set = {predicate(it)}

class MD3 {

	@Test
	fun `Set contains a given element`() {
		val set: Set = { it == 1 }
	print(set.contains(1))
	assert(set contains 1)
	}

	@Test
	fun `Singleton set contains a given element`() {
		val set: Set = singletonSet(2)
				assert(set contains 2)
	}

	@Test
	fun `Union set contains both elements`() {
		val set = singletonSet(1) union singletonSet(2)
				print(set.contains(1))
				assert(set contains 1)
				assert(set contains 2)
	}

	@Test
	fun `Union set contains three elements`() {
		val set1 = singletonSet(1)
				val set2 = singletonSet(2)
				val set3 = set1 union set2

				assert(set1 contains 1)
				assert(set2 contains 2)
				assert(set3 contains 2)


				val set = singletonSet(1) union singletonSet(2)
				assert(set contains 1)
				assert(set contains 2)
	}

	@Test
	fun `Intersect set works correctly`() {
		val set = singletonSet(1) union singletonSet(2) intersect singletonSet(1)
				assert(set contains 1)
				assert((set contains 2).not())
	}

	@Test
	fun `Diff set works correctly`() {
		val set = singletonSet(1) union singletonSet(2) union singletonSet(3) diff singletonSet(2)
				assert(set contains 1)
				assert(set contains 3)
				assert((set contains 2).not())
	}

	@Test
	fun `Filter set works correctly`() {
		val set = (singletonSet(1) union singletonSet(2)).filter { it == 1 }
		assert(set contains 1)
		assert((set contains 2).not())
	}

	@Test
	fun `Exists set works correctly`() {
		val set = singletonSet(1) union singletonSet(2)
				assert(set.exists { it == 1 })
				assert(set.exists { it == 2 })
	}

	@Test
	fun `Map set works correctly`() {
		val set = singletonSet(1).map { it * -1 }
		assert(set contains -1)
	}

	@Test
	fun `For all set works correctly`() {
		val set = singletonSet(2) union singletonSet(4) union singletonSet(6)
				assert(set.forAll { it % 2 == 0 })
				assert((set.forAll { sqrt(it.toFloat()).toInt() % 2 == 0 }).not())
	}

	@Test
	fun `Content works correctly`() {
		val set = singletonSet(1) union singletonSet(2)
				assert(set.contents == "{1,2}")
	}
}