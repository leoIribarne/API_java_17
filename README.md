Meu primeiro projeto de API em java 17 com maven.. 

```mermaid
classDiagram
    class Header {
        +Name: string
        +AccountBalance: string
    }
    class CartSection {
        +IconCarrinho: string
        +ProductCount: number
        +TotalValue: number
    }
    class InputSection {
        +Instruction: string
        +InputField: string
        +AddButton: string
    }
    class User {
    }

    User *-- Header
    User *-- CartSection
    User *-- InputSection
 

```
