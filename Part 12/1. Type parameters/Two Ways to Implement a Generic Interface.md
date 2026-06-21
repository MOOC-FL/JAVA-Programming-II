#### Two Ways to Implement a Generic Interface
#### Two Ways to Implement a Generic Interface — Full Breakdown
#### 1. What They Look Like in Code

| Aspect | Way 1 — Fixed Type | Way 2 — Pass-Through Type |
|---|---|---|
| Interface | `List<Movie>` | `List<T>` |
| Class declaration | `class MovieList implements List<Movie>` | `class MyList<T> implements List<T>` |
| Type parameter on class | ❌ None | ✅ `<T>` after class name |
| Who decides the type | The class itself | The user at instantiation |

#### 2. Method Signatures Inside the Class

| Method | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| Add element | `add(Movie movie)` | `add(T element)` |
| Get element | `Movie get(int index)` | `T get(int index)` |
| Remove element | `remove(Movie movie)` | `remove(T element)` |
| Contains | `contains(Movie movie)` | `contains(T element)` |

#### 3. How You Instantiate Them

| | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| Syntax | `new MovieList()` | `new MyList<Movie>()` |
| With Movies | ✅ `MovieList movies = new MovieList()` | ✅ `MyList<Movie> movies = new MyList<>()` |
| With Strings | ❌ Not possible | ✅ `MyList<String> names = new MyList<>()` |
| With Integers | ❌ Not possible | ✅ `MyList<Integer> nums = new MyList<>()` |
| With any type | ❌ | ✅ |

#### 4. Behavior & Constraints

| | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| Type safety | ✅ Always `Movie` | ✅ Whatever `T` is |
| Adding wrong type | Compile error | Compile error |
| Casting needed | ❌ Never | ❌ Never |
| Flexibility | ❌ One type forever | ✅ Any type per instance |
| Specialization | ✅ Can add Movie-specific methods | ❌ Only generic operations |

#### 5. When to Use Which

| Situation | Use Way 1 | Use Way 2 |
|---|---|---|
| Class is domain-specific | ✅ | ❌ |
| Class is a utility/container | ❌ | ✅ |
| Need extra methods for that type | ✅ e.g. `filterByGenre()` | ❌ |
| Need to reuse across many types | ❌ | ✅ |
| Building a framework/library | ❌ | ✅ |

#### 6. Real Java Standard Library Examples

| Class / Interface | Way | How |
|---|---|---|
| `String` | Way 1 | `implements Comparable<String>` — fixed to String |
| `Integer` | Way 1 | `implements Comparable<Integer>` — fixed to Integer |
| `ArrayList<E>` | Way 2 | `implements List<E>` — passes `E` through |
| `HashMap<K,V>` | Way 2 | `implements Map<K,V>` — passes both through |
| `MovieList` | Way 1 | `implements List<Movie>` — fixed to Movie |
## Deep Dive — Two Ways to Implement a Generic Interface


#### 1. Anatomy of the Declaration

| Part | Way 1 — Fixed | Way 2 — Pass-Through |
|---|---|---|
| Keyword `class` | `class` | `class` |
| Class name | `MovieList` | `MyList` |
| Type parameter on class | ❌ absent | ✅ `<T>` |
| `implements` keyword | `implements` | `implements` |
| Interface name | `List` | `List` |
| Interface type argument | `<Movie>` hardcoded | `<T>` forwarded |
| Full declaration | `class MovieList implements List<Movie>` | `class MyList<T> implements List<T>` |


#### 2. Field Declarations Inside the Class

| Field | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| Internal storage | `Movie[] elements` | `T[] elements` |
| Size tracker | `int size` | `int size` |
| Type of stored data | Always `Movie` | Whatever `T` resolves to |
| Can store `String`? | ❌ | ✅ when `T = String` |
| Can store `Movie`? | ✅ always | ✅ when `T = Movie` |

#### 3. Constructor Differences

| Aspect | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| Constructor signature | `MovieList()` | `MyList()` |
| Type info at construction | Already known (`Movie`) | Provided by caller |
| Example | `new MovieList()` | `new MyList<Movie>()` |
| Diamond operator `<>` | ❌ not needed | ✅ `new MyList<>()` |
| Type inferred by compiler | N/A | ✅ from left-hand side |


#### 4. Every Method Signature Compared

| Method | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| `add` | `boolean add(Movie e)` | `boolean add(T e)` |
| `get` | `Movie get(int i)` | `T get(int i)` |
| `set` | `Movie set(int i, Movie e)` | `T set(int i, T e)` |
| `remove by index` | `Movie remove(int i)` | `T remove(int i)` |
| `remove by object` | `boolean remove(Movie o)` | `boolean remove(T o)` |
| `contains` | `boolean contains(Movie o)` | `boolean contains(T o)` |
| `indexOf` | `int indexOf(Movie o)` | `int indexOf(T o)` |
| `iterator` | `Iterator<Movie> iterator()` | `Iterator<T> iterator()` |
| `toArray` | `Movie[] toArray()` | `T[] toArray()` |
| `subList` | `List<Movie> subList(int, int)` | `List<T> subList(int, int)` |

#### 5. Type Resolution at Compile Time

| Scenario | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| Type known at | Class definition time | Instantiation time |
| Resolved by | The class author | The class user |
| `T` in bytecode | Erased to `Movie` | Erased to `Object` |
| Compiler inserts casts | To `Movie` | To `Object` then actual type |
| Type visible at runtime | ❌ erased | ❌ erased |

#### 6. What You Can and Cannot Do

| Operation | Way 1 — Fixed | Way 2 — Generic |
|---|---|---|
| Add a `Movie` | ✅ | ✅ when `T = Movie` |
| Add a `String` | ❌ compile error | ✅ when `T = String` |
| Call `movie.getTitle()` inside class | ✅ directly | ❌ `T` doesn't know that |
| Cast inside class needed | ❌ | ❌ |
| Add Movie-specific methods | ✅ e.g. `filterByGenre()` | ❌ `T` is unknown |
| Use with `Collections.sort()` | ✅ if `Movie` is Comparable | ✅ if `T` is Comparable |
| Use with `Collections.shuffle()` | ✅ | ✅ |

#### 7. Adding Domain-Specific Methods (Way 1 Advantage)

| Method | Only Possible In | Reason |
|---|---|---|
| `filterByGenre(String genre)` | Way 1 | Knows type is `Movie` |
| `sortByReleaseYear()` | Way 1 | Can access `Movie` fields |
| `getHighestRated()` | Way 1 | Can call `movie.getRating()` |
| `findByDirector(String name)` | Way 1 | Can call `movie.getDirector()` |
| `add(T element)` | Way 2 | Works for any `T` |
| `get(int index)` | Way 2 | Works for any `T` |

#### 8. Extending Further

| Scenario | Way 1 Result | Way 2 Result |
|---|---|---|
| Subclass fixes type | `class TopMovies extends MovieList` | `class TopMovies extends MyList<Movie>` |
| Subclass stays generic | N/A (already fixed) | `class TopList<T> extends MyList<T>` |
| Subclass changes type | ❌ cannot un-fix | ✅ `class NameList extends MyList<String>` |
| Used as `List<Movie>` | ✅ | ✅ when `T = Movie` |
| Used as `List<?>` | ✅ | ✅ |

#### 9. Real World Java Examples

| Class | Way | Declaration | Fixed To |
|---|---|---|---|
| `String` | 1 | `implements Comparable<String>` | `String` |
| `Integer` | 1 | `implements Comparable<Integer>` | `Integer` |
| `Double` | 1 | `implements Comparable<Double>` | `Double` |
| `Boolean` | 1 | `implements Comparable<Boolean>` | `Boolean` |
| `ArrayList` | 2 | `implements List<E>` | anything |
| `LinkedList` | 2 | `implements List<E>` | anything |
| `HashSet` | 2 | `implements Set<E>` | anything |
| `HashMap` | 2 | `implements Map<K,V>` | anything |
| `TreeMap` | 2 | `implements Map<K,V>` | anything |

#### 10. Summary Scorecard

| Criteria | Way 1 — Fixed | Way 2 — Pass-Through |
|---|---|---|
| Reusability | ⭐ | ⭐⭐⭐⭐⭐ |
| Domain specificity | ⭐⭐⭐⭐⭐ | ⭐ |
| Flexibility | ⭐ | ⭐⭐⭐⭐⭐ |
| Simplicity of use | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| Access to type methods | ⭐⭐⭐⭐⭐ | ⭐ |
| Library/framework suitability | ⭐⭐ | ⭐⭐⭐⭐⭐ |
