#### Creating a new list
- Let's create class `List`. The List has a generic array
- the type of the elements in the array is defined on run time using type parameters.
- Let's set the size of the array to 10.
-  The array is created as type object, and changed to type generic with (A[]) new Object[10]; — this is done because Java does not support the call new A[10]; for now.
Good, let's dig into this — it's one of Java's classic quirks that trips people up the first time they hit it.

#### The core problem: type erasure

Java generics are implemented via **type erasure**. At compile time, generic type parameters (`A`, `T`, `E`, etc.) exist and are checked by the compiler — but at *runtime*, that information is gone. The JVM doesn't actually know what `A` was; it's erased down to `Object` (or the bound, if you specified one like `<A extends Number>`).

This matters enormously for arrays, because **Java arrays, unlike generics, are reified** — they *do* know their component type at runtime. Every array carries its type internally and enforces it (this is called covariant array typing + runtime checks). For example:

```java
Object[] arr = new String[5]; // legal, arr's runtime type is String[]
arr[0] = 42; // compiles (Object slot)... but throws ArrayStoreException at runtime!
```

The JVM checks every array store against the array's *actual* runtime component type.

#### Why `new A[10]` doesn't compile

To create an array, the JVM needs a concrete, real type to stamp onto that array at runtime (e.g. `String[]`, `Integer[]`). But since `A` is erased, the compiler has **no real class to give the array** — it would have to create... "an array of erased nothing." So Java simply forbids it:

```java
class List<A> {
    private A[] array = new A[10]; // compile-time ERROR
}
```

#### The workaround

```java
class List<A> {
    private A[] array;

    @SuppressWarnings("unchecked")
    public List() {
        array = (A[]) new Object[10];
    }
}
```

Here's what's happening step by step:

| Step | What it does |
|---|---|
| `new Object[10]` | Creates a real, reified array whose runtime type is `Object[]` |
| `(A[]) ...` | Casts the reference *at compile time* so the code can treat it as `A[]` |
| Result | `array` is a variable of *compile-time* type `A[]`, but its actual *runtime* type is still `Object[]` |

This compiles because of the erasure trick: at compile time the cast `(A[])` is really just `(Object[])` under the hood (since `A` erases to `Object`), so the compiler allows it — but it flags it as an **unchecked cast** because it can't verify it's actually safe. That's why you'll get a compiler warning, and why `@SuppressWarnings("unchecked")` is customarily added to silence it once you, the programmer, have manually verified the usage is safe.

#### Why this can bite you later

Because the array is *really* an `Object[]` at runtime, some things break:

```java
A[] array = (A[]) new Object[10];
String[] strings = (String[]) array; // ClassCastException at runtime!
```

Even though the compiler is convinced `array` is `A[]`, the JVM knows it's actually `Object[]`, and casting it down to something more specific like `String[]` fails at runtime.

#### Practical implications for your `List` class

- **Internal use only**: as long as `array` stays `private` and you only read/write elements through your own class's methods (which the compiler type-checks against `A` at compile time), you're safe in practice — external code never sees the "lie."
- **Never `return array;` as `A[]`** to outside callers if they might try to cast it further, since the runtime type mismatch can surface.
- This is precisely why classes like `ArrayList<E>` internally store `Object[]` rather than `E[]`, and cast individual elements to `E` on retrieval (`(E) array[index]`) instead of casting the whole array once.

Want me to walk through implementing `get`/`set`/`add` methods for this `List` class next, so you can see how the per-element casting pattern plays out in practice?
#### Creating a new list
- Let's create class `List`. The List has a generic array — the type of the elements in the array is defined on run time using type parameters. Let's set the size of the array to `10`. The array is created as type object, and changed to type generic with `(A[]) new Object[10];` — this is done because Java does not support the call `new A[10];` for now.

```java
public class List<Type> {
    private Type[] values;

    public List() {
        this.values = (Type[]) new Object[10];
    }
}
```
> List encapsulates an array. In the beginning, every element in the array contains a null-reference.

