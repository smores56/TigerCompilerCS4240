grammar Tiger;
tiger_program : MAIN LET declaration_segment IN BEGIN stat_seq END EOF ;
WS : (' ' | '\t' | '\n')+ -> channel(HIDDEN);
COMMENT : '/*' ('a'..'z' | 'A'..'Z' | '0'..'9' | ' ' | '\t' | '\n')* '*/' -> channel(HIDDEN);
VAR : 'var' ;
MAIN : 'main' ;
SEMI : ';' ;
COMMA : ',' ;
EQUALS : '=' ;
OF : 'of' ;
ARRAY : 'array' ;
RECORD : 'record' ;
END : 'end' ;
LBRACKET : '[' ;
RBRACKET : ']' ;
COLON : ':' ;
ASSIGN : ':=' ;
FUNCTION : 'function' ;
LPARENS : '(' ;
RPARENS : ')' ;
BEGIN : 'begin' ;
IF : 'if' ;
ENDIF : 'endif' ;
THEN : 'then' ;
ELSE : 'else' ;
WHILE : 'while' ;
DO : 'do' ;
ENDDO : 'enddo' ;
FOR : 'for' ;
TO : 'to' ;
BREAK : 'break' ;
RETURN : 'return' ;
LET : 'let' ;
IN : 'in' ;
DOT : '.' ;
POWER : '**' ;
PLUS : '+' ;
MINUS : '-' ;
MULT : '*' ;
DIV : '/' ;
NEQ : '<>' ;
LESS : '<' ;
GREATER : '>' ;
LTEQ : '<=' ;
GTEQ : '>=' ;
AND : '&' ;
OR : '|' ;
TYPE : 'type' ;
INT_KW : 'int' ;
FLOAT_KW : 'float' ;
ID : ('a'..'z' | 'A'..'Z') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')* ;
INT : '0'..'9'+ ;
FLOAT : '0'..'9'+ '.' '0'..'9'+ ;

declaration_segment : type_declaration_list var_declaration_list funct_declaration_list ;

type_declaration_list : type_declaration type_declaration_list | /* epsilon */ ;

var_declaration_list : var_declaration var_declaration_list | /* epsilon */ ;

funct_declaration_list : funct_declaration funct_declaration_list | /* epsilon */ ;

type_declaration : TYPE ID EQUALS type SEMI ;

type : type_id | ARRAY LBRACKET INT RBRACKET OF type_id  | RECORD field_list END | ID ;

type_id : INT_KW | FLOAT_KW ;
field_list : ID COLON type_id SEMI field_list | /* epsilon */ ;

var_declaration : VAR id_list COLON type optional_init SEMI ;
id_list : ID id_list_tail ;
id_list_tail :  COMMA ID id_list_tail | /* epsilon */ ;

optional_init : ASSIGN constant | /* epsilon */ ;

funct_declaration : FUNCTION ID LPARENS param_list RPARENS ret_type BEGIN stat_seq END SEMI ;
param_list : param param_list_tail | /* epsilon */ ;
param_list_tail : COMMA param param_list_tail | /* epsilon */ ;

ret_type : type | /* epsilon */ ;
param : ID COLON type ;

stat_seq : stat stat_seq_tail ;
stat_seq_tail : stat stat_seq_tail | /* epsilon */ ;

stat : assign_or_func
     | stat_tail_a
     | WHILE expr DO stat_seq ENDDO SEMI
     | FOR ID ASSIGN expr TO expr DO stat_seq ENDDO SEMI
     | BREAK SEMI
     | RETURN expr SEMI
     | LET declaration_segment IN stat_seq END ;

stat_tail_a : IF expr THEN stat_seq stat_tail_b ;
stat_tail_b : ENDIF SEMI | ELSE stat_seq ENDIF SEMI ;

assign_or_func : ID aof_tail ;
aof_tail : LBRACKET expr RBRACKET ASSIGN expr SEMI
         | DOT ID ASSIGN expr SEMI
         | ASSIGN expr SEMI
         | LPARENS expr_list RPARENS SEMI ;

constant : sign constant_tail ;
sign : PLUS | MINUS | /* epsilon */ ;
constant_tail : INT | FLOAT ;

expr : logic_expr ((AND|OR) logic_expr)* ;
logic_expr : cond_expr ((EQUALS|NEQ|LESS|GREATER|LTEQ|GTEQ) cond_expr)* ;
cond_expr : term ((PLUS|MINUS) term)* ;
term : factor ((MULT|DIV) factor)* ;
factor : atom factor_tail ;
factor_tail : POWER factor factor_tail
            | /* epsilon */ ;
atom : constant
     | LPARENS expr RPARENS
     | ID atom_tail ;
atom_tail : LPARENS expr_list RPARENS
          | LBRACKET expr RBRACKET
          | /* epsilon */ ;

expr_list : expr expr_list_tail | /* epsilon */ ;
expr_list_tail : COMMA expr expr_list_tail | /* epsilon */ ;
