package iii_conventions

import util.TODO


/**
 * Solution from the answers. This initially made no sense to me whatsoever.
 *
 * Turns out invoke can be called on any instance of a class without a method name!
 *
 * So if val foo = Invokable(0) you can call invoke using foo() rather than foo.invoke().
 *
 * It appears that successive invocations can be made my specifying multiple ()s e.g.
 *  foo()()() // calls invoke 3 times
 *
 * This looks like Scala's apply - but the multiple invocations are surprising to me at least.
 *
 * More info here
 *   - http://joshskeen.com/kotlins-invoke-operator/
 *   - https://www.51zero.com/blog/2016/4/14/kotlin-for-scala-developers
 */
class Invokable(private var invocations: Int = 0) {

    operator fun invoke(): Invokable {
        invocations++
        return this
    }

    fun getNumberOfInvocations() = invocations
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
