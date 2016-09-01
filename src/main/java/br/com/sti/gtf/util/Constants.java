package br.com.sti.gtf.util;

/**
 *
 * @author Phelipe Melanias
 */
interface IConstant {
    String TITLE = "title";
    String SUBTITLE = "subTitle";
    String EDIT_TITLE = "editTitle";
    String VIEW_TITLE = "viewTitle";

    String INDEX_TITLE = "Início";

    String APPLICATIONS = "applications";

    //Item
    String ITEM_TITLE = "Item";
    String ITEM_LIST  = "Lista de itens";
    String ITEM_VIEW  = "Informações do item";
    String ITEM_ADD   = "Cadastrar item";
    String ITEM_EDIT  = "Editar item";

    //Computador
    String COMPUTER_TITLE  = "Computadores";
    String COMPUTER_LIST   = "Lista de computadores";
    String COMPUTER_IMPORT = "Importar computadores";
    String COMPUTER_VIEW   = "Informações do computador";

    //NGTEL
    String INVOICES = "faturas";
    String CONTRACTS = "contratos";
    String FIXED_TITLE = "Telefonia fixa";
    String FIXED_CONTRACT_EDIT  = "Editar contrato";
    String FIXED_INVOICES_LIST   = "Lista de faturas";
    String FIXED_CONTRACTS_LIST  = "Lista de contratos";
    String FIXED_INVOICES_IMPORT = "Importar fatura(s)";
    String FIXED_INVOICE_DETAILS = "Detalhamento da fatura";

    //Indexes
    String TOTAL_SECTORS   = "totalSectors";
    String TOTAL_EMPLOYEES = "totalEmployees";
    String TOTAL_COMPUTERS = "totalComputers";
    String TOTAL_TRANSFERS = "totalTransfers";

    String TOTAL_PROCESSES = "totalProcesses";
    String TOTAL_CABINETS  = "totalCabinets";
    String TOTAL_UPLOADED_FILES = "totalUploadedFiles";

    String TOTAL_ITEMS     = "totalItems";
    String TOTAL_TRADING   = "totalTrading";
    String TOTAL_ENTITIES  = "totalEntities";
    String TOTAL_SUPPLIERS = "totalSuppliers";

    String TOTAL_FIXED_VELOX        = "totalVelox";
    String TOTAL_FIXED_TOTAL_MULTA  = "totalMulta";
    String TOTAL_FIXED_TOTAL_FATURA = "totalFatura";
    String TOTAL_FIXED_MEIOS_ACESSO = "totalMeiosDeAcessoDaFatura";
    String TOTAL_FIXED_BILLS     = "totalFixedBills";
    String TOTAL_FIXED_CONTRACTS = "totalFixedContracts";

    //REGEX
    String FIXA_FATURA_DATA = "\\d{2}/\\d{4}";
    String FIXA_FATURA_TITULAR  = "^\\D{0,50}";
    String FIXA_FATURA_CONTRATO = "C\\d{7,10}";
    String FIXA_FATURA_VALOR_TOTAL = "\\d{0,3}\\.?\\d{0,3}\\.?\\d{1,3},\\d{2}";
    String FIXA_FATURA_MEIOS_DE_ACESSO = "\\d{1,8}";

    String FIXA_FATURA_LINHA            = "\\s\\d{4}-\\d{4}\\s";         //9999-9999
    String FIXA_FATURA_LINHA_UTIL       = "-\\d{3}\\s";                  //-999
    String FIXA_FATURA_LINHA_VELOX      = "\\s\\d{7}\\s";                //9999999
    String FIXA_FATURA_LINHA_MULTA      = "S\\d{3}-\\d{4}\\s";           //S999-9999
    String FIXA_FATURA_LINHA_DIVERSA    = "\\s\\d{3}-\\d{4}\\s";         //999-9999
    String FIXA_FATURA_LINHA_GRATUITA   = "\\s\\d{7}-\\d{4}\\s";         //99999999-9999
    String FIXA_FATURA_LINHA_VALOR      = "\\d{0,3}\\.?\\d{1,3},\\d{2}"; //XXX.XX9,99
    String FIXA_FATURA_TIPO_DE_ACESSO   = "^\\D+";
    String FIXA_FATURA_DATA_DE_ATIVACAO = "\\d{2}/\\d{2}/\\d{4}";        //99/99/9999
}

public final class Constants implements IConstant {
    private Constants() {}
}