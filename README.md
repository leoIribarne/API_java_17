Meu primeiro projeto de API em java 17 com maven.. 

```mermaid
classDiagram
    class User {
        +Name: string
    }
    class Account {
        +AccountCode: string
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

   

    User *-- Account
    User *-- CartSection
    User *-- InputSection
 

```
