import java.util.ArrayList;
import java.util.List;

class TablaSimbolos {
    private List<Symbol> symbols;

    public TablaSimbolos() {
        this.symbols = new ArrayList<>();
    }

    public void addSymbol(int line, String identifier, String dataType) {
        if (!symbolExists(identifier)) {
            symbols.add(new Symbol(line, identifier, dataType));
        } else {
            System.out.println("Error: El simbolo " + identifier + " ya existe.");
        }
    }

    public boolean symbolExists(String identifier) {
        return getSymbol(identifier) != null;
    }

    public Symbol getSymbol(String identifier) {
        for (Symbol symbol : symbols) {
            if (symbol.getIdentifier().equals(identifier)) {
                return symbol;
            }
        }
        return null;
    }

    public void assignValue(String identifier, Object value) {
        Symbol symbol = getSymbol(identifier);
        if (symbol != null) {
            if (value.getClass().getSimpleName().equals(symbol.getDataType())) {
                symbol.setValue(value);
            } else {
                System.out.println("Error en la linea " + symbol.getLine() + ": Tipo de dato incorrecto en la asignación para " + identifier + ". Esperado: " + symbol.getDataType() + ", Obtenido: " + value.getClass().getSimpleName());
            }
        } else {
            System.out.println("Error: El simbolo " + identifier + " no esta definido.");
        }
    }
    

    // Clase interna Symbol
    public static class Symbol {
        private int line; // línea donde se encuentra el símbolo.
        private String identifier; // nombre del identificador.
        private String dataType; // tipo de dato.
        private Object value;

        public Symbol(int line, String identifier, String dataType) {
            this.line = line;
            this.identifier = identifier;
            this.dataType = dataType;
            this.value = null; 
        }

        // Getters para Symbol
        public int getLine() {
            return line;
        }

        public String getIdentifier() {
            return identifier;
        }

        public String getDataType() {
            return dataType;
        }

        public Object getValue() {
            return value;
        }

        // Setters para Symbol
        public void setLine(int line) {
            this.line = line;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }
        public void setValue(Object value) {
            this.value = value;
        }
    }

    // Otros métodos útiles como la búsqueda de símbolos, eliminación, etc.
    

public void imprimirTabla() {
    System.out.println("---- Tabla de Simbolos ----");
    System.out.println("Linea | Identificador | Tipo de Dato");
    System.out.println("-----------------------------------");
    for (Symbol symbol : symbols) {
        System.out.println(symbol.getLine() + " | " + symbol.getIdentifier() + " | " + symbol.getDataType());
    }

}

public int getDeclarationLine(String identifier) {
    Symbol symbol = getSymbol(identifier);
    if (symbol != null) {
        return symbol.getLine();
    }
    return -1; // Indica que el símbolo no fue encontrado
}

}