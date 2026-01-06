## Heap vs. Stack

These are two fundamental memory management concepts in computer systems:

### **Stack**
1. **Management**: Automatically managed by the compiler/OS
2. **Stores**:
   - Local variables
   - Function parameters
   - Return addresses
   - Temporary data
3. **Characteristics**:
   - **LIFO (Last-In-First-Out)** structure
   - Fixed size at compile time (typically 1-8 MB)
   - Very fast access (direct pointer manipulation)
   - Automatic allocation/deallocation (function calls/returns)
   - Memory is contiguous
   - Each thread has its own stack

### **Heap**
1. **Management**: Manually managed by programmer (or garbage collector)
2. **Stores**:
   - Dynamically allocated memory
   - Object instances
   - Global data structures
   - Data that must outlive function scope
3. **Characteristics**:
   - Dynamically allocated at runtime
   - Size limited by available system memory
   - Slower access (indirect pointer access)
   - Manual memory management required (in some languages)
   - Can fragment over time
   - Shared across threads

## Example Comparison

### C/C++ Example
```c
// Stack allocation
int x = 5;  // On stack

// Heap allocation
int* y = (int*)malloc(sizeof(int));  // On heap
*y = 10;
free(y);  // Must manually free
```

### Java Example
```java
public class Example {
    // Primitive on stack (when inside method)
    int stackVar = 10;
    
    // Object on heap, reference on stack
    String heapVar = new String("Hello");
    
    void method() {
        int local = 20;  // On stack
        Object obj = new Object();  // Object on heap, reference on stack
    }
}
```

## Key Differences Table

| Aspect | Stack | Heap |
|--------|-------|------|
| **Management** | Automatic | Manual/GC |
| **Size** | Small, fixed | Large, flexible |
| **Speed** | Very fast | Slower |
| **Lifetime** | Function scope | Until explicitly freed |
| **Fragmentation** | None | Possible |
| **Thread Safety** | Per-thread | Shared |
| **Allocation Pattern** | Contiguous | Random |
| **Access Pattern** | Direct | Through pointers |

## Memory Layout Visualization
```
High Addresses
┌─────────────────┐
│       Heap      │ ← Grows upward
│                 │
├─────────────────┤
│   (Unused)      │
├─────────────────┤
│       Stack     │ ← Grows downward
└─────────────────┘
Low Addresses
```

## Practical Considerations

### When to use Stack?
- Small, temporary variables
- Function call management
- Data with predictable lifespan
- Performance-critical sections

### When to use Heap?
- Large data structures
- Data shared across functions
- Dynamic/unknown size at compile time
- Objects needing extended lifetime

### Common Issues
1. **Stack Overflow**: Excessive recursion or large local variables
2. **Memory Leaks**: Heap memory not freed
3. **Dangling Pointers**: Accessing freed heap memory
4. **Fragmentation**: Frequent small allocations on heap

## Language-Specific Notes

- **C/C++**: Manual heap management with `malloc/free` or `new/delete`
- **Java/C#**: Automatic garbage collection for heap, primitives on stack
- **Python/JavaScript**: Heap used for nearly everything, automatic GC
- **Rust**: Unique ownership model prevents many heap/stack issues

## Best Practices

1. Prefer stack allocation when possible (faster, safer)
2. Use heap for large or long-lived data
3. Always match allocations with deallocations
4. Be mindful of stack size limits in recursive algorithms
5. Use smart pointers (C++) or automatic GC languages to reduce heap errors

Understanding heap vs. stack is crucial for writing efficient, memory-safe programs, especially in systems programming and performance-critical applications.
