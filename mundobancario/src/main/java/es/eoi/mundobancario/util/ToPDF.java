package es.eoi.mundobancario.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import es.eoi.mundobancario.dto.AmortizacionDto;
import es.eoi.mundobancario.dto.ClienteCuentasMovimientosDto;
import es.eoi.mundobancario.dto.CuentaMovimientosDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoClienteDto;
import es.eoi.mundobancario.enums.TiposMovimiento;

public class ToPDF {

	public static void convertReportsCliente(ClienteCuentasMovimientosDto c, Integer id)
			throws FileNotFoundException, DocumentException {
		Document documento = new Document();

		String nombrePDF = "EOI_BANK_CLIENTE_" + id.toString() + ".pdf";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		FileOutputStream ficheroPDF = new FileOutputStream(nombrePDF);

		PdfWriter.getInstance(documento, ficheroPDF);

		documento.open();

		String cliente = "Usuario: " + c.getUsuario() + "\nNombre: " + c.getNombre() + "\nEmail: " + c.getEmail()
				+ "\nCuentas:\n\n";

		Paragraph p1 = new Paragraph(cliente, FontFactory.getFont("arial", 12, BaseColor.BLACK));
		documento.add(p1);

		for (CuentaMovimientosDto cuenta : c.getCuantas()) {
			PdfPTable tabla = new PdfPTable(3);
			tabla.addCell("Numero de cuenta");
			tabla.addCell("Alias");
			tabla.addCell("Saldo");

			tabla.addCell(cuenta.getNum_cuenta().toString());
			tabla.addCell(cuenta.getAlias());
			tabla.addCell(cuenta.getSaldo().toString());

			documento.add(tabla);

			Paragraph p2 = new Paragraph("      Movimientos:\n\n", FontFactory.getFont("arial", 12, BaseColor.BLACK));
			documento.add(p2);

			PdfPTable tabla2 = new PdfPTable(4);
			tabla2.addCell("Descripción");
			tabla2.addCell("Fecha");
			tabla2.addCell("Importe");
			tabla2.addCell("Tipo movimintos");

			for (MovimientoDto mov : cuenta.getMovimientos()) {

				tabla2.addCell(mov.getDescripcion());
				tabla2.addCell(sdf.format(mov.getFecha()));
				tabla2.addCell(mov.getImporte().toString());
				PdfPCell cell;
				if (mov.getTipoMovimiento().getTipo().equals(TiposMovimiento.INGRESO.toString())
						|| mov.getTipoMovimiento().getTipo().equals(TiposMovimiento.PRESTAMO.toString())) {
					cell = new PdfPCell(new Phrase(mov.getTipoMovimiento().getTipo()));
					cell.setBackgroundColor(BaseColor.GREEN);
				} else {
					cell = new PdfPCell(new Phrase(mov.getTipoMovimiento().getTipo()));
					cell.setBackgroundColor(BaseColor.RED);
				}
				tabla2.addCell(cell);
			}
			documento.add(tabla2);
			Paragraph p3 = new Paragraph("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\n");
			p3.setAlignment(Element.ALIGN_CENTER);
			documento.add(p3);
		}
		documento.close();
	}

	public static void convertReportsPrestamo(PrestamoClienteDto p, Integer id)
			throws FileNotFoundException, DocumentException {
		Document documento = new Document();

		String nombrePDF = "EOI_BANK_PRESTAMO_" + id.toString() + ".pdf";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		FileOutputStream ficheroPDF = new FileOutputStream(nombrePDF);

		PdfWriter.getInstance(documento, ficheroPDF);

		documento.open();

		String cliente = "Descripción: " + p.getDescripcion() + "\nFecha: " + sdf.format(p.getFecha()) + "\nImporte: "
				+ p.getImporte().toString() + "\nPlazos: " + p.getPlazos() + "\nAmortizaciones:\n\n";

		Paragraph p1 = new Paragraph(cliente, FontFactory.getFont("arial", 12, BaseColor.BLACK));
		documento.add(p1);

		PdfPTable tabla = new PdfPTable(2);
		tabla.addCell("Fecha");
		tabla.addCell("Importe");

		for (AmortizacionDto am : p.getAmortizaciones()) {

			tabla.addCell(sdf.format(am.getFecha()));
			tabla.addCell(am.getImporte().toString());
		}

		documento.add(tabla);

		documento.close();
	}

}
