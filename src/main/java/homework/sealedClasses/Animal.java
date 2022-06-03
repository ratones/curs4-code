package homework.sealedClasses;

sealed interface Animal permits Dog, Cat, Bird, Insects { }

final class Dog implements Animal { }

record Cat() implements Animal { }

sealed class Bird implements Animal permits Canary,  Parrot, Chicken { }

// Nu se poate crea record pentru Canary pentru ca nu poate extinde clase, doar implementa interfata
final class Canary extends Bird { }

final class Parrot extends Bird { }

non-sealed class Chicken extends Bird { }

non-sealed interface Insects extends Animal { }

record LadyBug() implements Insects { }

class Fly implements Insects { }
