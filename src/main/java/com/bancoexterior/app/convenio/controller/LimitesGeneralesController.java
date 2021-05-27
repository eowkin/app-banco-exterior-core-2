package com.bancoexterior.app.convenio.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoexterior.app.convenio.apiRest.ILimitesGeneralesServiceApirest;
import com.bancoexterior.app.convenio.apiRest.IMonedaServiceApiRest;
import com.bancoexterior.app.convenio.dto.LimiteRequest;
import com.bancoexterior.app.convenio.dto.MonedasRequest;
import com.bancoexterior.app.convenio.exception.CustomException;
import com.bancoexterior.app.convenio.model.LimitesGenerales;
import com.bancoexterior.app.convenio.model.Moneda;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/limitesGenerales")
public class LimitesGeneralesController {

	@Autowired
	private ILimitesGeneralesServiceApirest limitesGeneralesServiceApirest;
	
	@Autowired
	private IMonedaServiceApiRest monedaServiceApiRest;
	
	
	@GetMapping("/index")
	public String index(Model model, RedirectAttributes redirectAttributes) {
		log.info("si me llamo a index listaLimitesWs");
		
		
		
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		LimitesGenerales limite = new LimitesGenerales();
		//limite.setFlagActivo(false);
		limiteRequest.setLimite(limite);
		
		try {
			List<LimitesGenerales> listaLimitesGenerales = limitesGeneralesServiceApirest.listaLimitesGenerales(limiteRequest);
			model.addAttribute("listaLimitesGenerales", listaLimitesGenerales);
    		return "convenio/limitesGenerales/listaLimitesGenerales";
		} catch (CustomException e) {
			
			log.error("error: "+e);
			return "redirect:/";
		}
	}
	
	@GetMapping("/activar/{codMoneda}/{tipoTransaccion}/{tipoCliente}")
	public String activarWs(@PathVariable("codMoneda") String codMoneda, @PathVariable("tipoTransaccion") String tipoTransaccion,
			@PathVariable("tipoCliente") String tipoCliente, LimitesGenerales limitesGenerales ,Model model, RedirectAttributes redirectAttributes) {
		log.info("activarWs");
		log.info(codMoneda);
		log.info(tipoTransaccion);
		log.info(tipoCliente);
		
		LimitesGenerales limitesGeneralesEdit = new LimitesGenerales();
				
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		LimitesGenerales limite = new LimitesGenerales();
		limite.setCodMoneda(codMoneda);
		limite.setTipoTransaccion(tipoTransaccion);
		limite.setTipoCliente(tipoCliente);
		limiteRequest.setLimite(limite);
		
		try {
			limitesGeneralesEdit = limitesGeneralesServiceApirest.buscarLimitesGenerales(limiteRequest);
			limitesGeneralesEdit.setFlagActivo(true);
			limiteRequest.setLimite(limitesGeneralesEdit);
			String respuesta = limitesGeneralesServiceApirest.actualizar(limiteRequest);
			redirectAttributes.addFlashAttribute("mensaje", respuesta);
			return "redirect:/limitesGenerales/index";
		} catch (CustomException e) {
			log.error("error: "+e);
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/limitesGenerales/index";
		}
		
		
	}	
	
	@GetMapping("/desactivar/{codMoneda}/{tipoTransaccion}/{tipoCliente}")
	public String desactivarWs(@PathVariable("codMoneda") String codMoneda, @PathVariable("tipoTransaccion") String tipoTransaccion,
			@PathVariable("tipoCliente") String tipoCliente, LimitesGenerales limitesGenerales ,Model model, RedirectAttributes redirectAttributes) {
		log.info("activarWs");
		log.info(codMoneda);
		log.info(tipoTransaccion);
		log.info(tipoCliente);
		
		LimitesGenerales limitesGeneralesEdit = new LimitesGenerales();
				
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		LimitesGenerales limite = new LimitesGenerales();
		limite.setCodMoneda(codMoneda);
		limite.setTipoTransaccion(tipoTransaccion);
		limite.setTipoCliente(tipoCliente);
		limiteRequest.setLimite(limite);
		
		try {
			limitesGeneralesEdit = limitesGeneralesServiceApirest.buscarLimitesGenerales(limiteRequest);
			limitesGeneralesEdit.setFlagActivo(false);
			limiteRequest.setLimite(limitesGeneralesEdit);
			String respuesta = limitesGeneralesServiceApirest.actualizar(limiteRequest);
			redirectAttributes.addFlashAttribute("mensaje", respuesta);
			return "redirect:/limitesGenerales/index";
		} catch (CustomException e) {
			log.error("error: "+e);
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/limitesGenerales/index";
		}
		
		
	}
	
	
	@GetMapping("/detalle/{codMoneda}/{tipoTransaccion}/{tipoCliente}")
	public String detalleWs(@PathVariable("codMoneda") String codMoneda, @PathVariable("tipoTransaccion") String tipoTransaccion,
			@PathVariable("tipoCliente") String tipoCliente, LimitesGenerales limitesGenerales ,Model model, RedirectAttributes redirectAttributes) {
		log.info("detalleWs");
		log.info(codMoneda);
		log.info(tipoTransaccion);
		log.info(tipoCliente);
		
		LimitesGenerales limitesGeneralesEdit = new LimitesGenerales();
				
		
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		LimitesGenerales limite = new LimitesGenerales();
		limite.setCodMoneda(codMoneda);
		limite.setTipoTransaccion(tipoTransaccion);
		limite.setTipoCliente(tipoCliente);
		limiteRequest.setLimite(limite);
		
		try {
			limitesGeneralesEdit = limitesGeneralesServiceApirest.buscarLimitesGenerales(limiteRequest);
			if(limitesGeneralesEdit != null) {
				model.addAttribute("limitesGenerales", limitesGeneralesEdit);
            	return "convenio/limitesGenerales/formLimitesGeneralesDetalle";
			}else {
				redirectAttributes.addFlashAttribute("mensajeError", " Codigo : 0001 descripcion: Operacion Exitosa.La consulta no arrojo resultado.");
				return "redirect:/limitesGenerales/index";
			}
		} catch (CustomException e) {
			log.error("error: "+e);
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/limitesGenerales/index";
		}
		
	
	}
	
	@GetMapping("/edit/{codMoneda}/{tipoTransaccion}/{tipoCliente}")
	public String editarWs(@PathVariable("codMoneda") String codMoneda, @PathVariable("tipoTransaccion") String tipoTransaccion,
			@PathVariable("tipoCliente") String tipoCliente, LimitesGenerales limitesGenerales ,Model model, RedirectAttributes redirectAttributes) {
		log.info("editarWs");
		log.info(codMoneda);
		log.info(tipoTransaccion);
		log.info(tipoCliente);
		
		LimitesGenerales limitesGeneralesEdit = new LimitesGenerales();
				
		
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		LimitesGenerales limite = new LimitesGenerales();
		limite.setCodMoneda(codMoneda);
		limite.setTipoTransaccion(tipoTransaccion);
		limite.setTipoCliente(tipoCliente);
		limiteRequest.setLimite(limite);
		
		try {
			limitesGeneralesEdit = limitesGeneralesServiceApirest.buscarLimitesGenerales(limiteRequest);
			if(limitesGeneralesEdit != null) {
				model.addAttribute("limitesGenerales", limitesGeneralesEdit);
				return "convenio/limitesGenerales/formLimitesGeneralesEdit";
			}else {
				redirectAttributes.addFlashAttribute("mensajeError", " Codigo : 0001 descripcion: Operacion Exitosa.La consulta no arrojo resultado.");
				return "redirect:/limitesGenerales/index";
			}
		} catch (CustomException e) {
			log.error("error: "+e);
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/limitesGenerales/index";
		}
	}	
	
	@PostMapping("/guardar")
	public String guardarWs(LimitesGenerales limitesGenerales, BindingResult result,  RedirectAttributes redirectAttributes) {
		log.info("guardarWs");
		log.info("limitesGenerales: "+limitesGenerales);
		
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.info("Ocurrio un error: " + error.getDefaultMessage());
			}
			
			
			
			return "convenio/limitesGenerales/formLimitesGeneralesEdit";
		}
		
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		limiteRequest.setLimite(limitesGenerales);
		
		try {
			
			String respuesta = limitesGeneralesServiceApirest.actualizar(limiteRequest);
			redirectAttributes.addFlashAttribute("mensaje", respuesta);
			return "redirect:/limitesGenerales/index";
		} catch (CustomException e) {
			log.error("error: "+e);
			result.addError(new ObjectError("codMoneda", " Codigo :" +e.getMessage()));
			return "convenio/limitesGenerales/formLimitesGeneralesEdit";
		}
		
	
	}
	
	
	@GetMapping("/formLimitesGenerales")
	public String formLimitesGenerales(LimitesGenerales limitesGenerales,  Model model, RedirectAttributes redirectAttributes) {
		List<Moneda> listaMonedas = new ArrayList<>();
		
		MonedasRequest monedasRequest = new MonedasRequest();
		monedasRequest.setIdUsuario("test");
		monedasRequest.setIdSesion("20210101121213");
		monedasRequest.setCodUsuario("E66666");
		monedasRequest.setCanal("8");
		Moneda moneda = new Moneda();
		moneda.setFlagActivo(true);
		monedasRequest.setMoneda(moneda);
		
		try {
			listaMonedas = monedaServiceApiRest.listaMonedas(monedasRequest);
			model.addAttribute("listaMonedas", listaMonedas);
    		return "convenio/limitesGenerales/formLimitesGenerales";
		} catch (CustomException e) {
			log.error("error: "+e);
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/limitesGenerales/index";
		}
	}
	
	@PostMapping("/save")
	public String saveWs(LimitesGenerales limitesGenerales, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		log.info("saveWs");
		log.info("limitesGenerales: "+limitesGenerales);
		List<Moneda> listaMonedas = new ArrayList<>();
		MonedasRequest monedasRequest = new MonedasRequest();
		monedasRequest.setIdUsuario("test");
		monedasRequest.setIdSesion("20210101121213");
		monedasRequest.setCodUsuario("E66666");
		monedasRequest.setCanal("8");
		Moneda moneda = new Moneda();
		moneda.setFlagActivo(true);
		monedasRequest.setMoneda(moneda);
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.info("Ocurrio un error: " + error.getDefaultMessage());
			}
			
			try {
				listaMonedas = monedaServiceApiRest.listaMonedas(monedasRequest);
				model.addAttribute("listaMonedas", listaMonedas);
				return "convenio/limitesGenerales/formLimitesGenerales";
			} catch (CustomException e) {
				log.error("error: "+e);
				result.addError(new ObjectError("codMoneda", " Codigo :" +e.getMessage()));
				return "convenio/limitesGenerales/formLimitesGenerales";
			}
			
		}
		
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		limitesGenerales.setFlagActivo(true);
		limiteRequest.setLimite(limitesGenerales);
		
		try {
			String respuesta = limitesGeneralesServiceApirest.crear(limiteRequest);
			redirectAttributes.addFlashAttribute("mensaje", respuesta);
			return "redirect:/limitesGenerales/index";
		} catch (CustomException e) {
			log.error("error: "+e);
			try {
				listaMonedas = monedaServiceApiRest.listaMonedas(monedasRequest);
				model.addAttribute("listaMonedas", listaMonedas);
				result.addError(new ObjectError("codMoneda", " Codigo :" +e.getMessage()));
				return "convenio/limitesGenerales/formLimitesGenerales";
			} catch (CustomException e1) {
				log.error("error: "+e1);
				result.addError(new ObjectError("codMoneda", " Codigo :" +e.getMessage()));
				return "convenio/limitesGenerales/formLimitesGenerales";
			}
			
		}
	}
	
	@GetMapping("/search")
	public String search(@ModelAttribute("limitesGeneralesSearch") LimitesGenerales limitesGeneralesSearch, 
			Model model, RedirectAttributes redirectAttributes) {
		log.info("si me llamo a search limitesGeneralesWs");
		log.info(limitesGeneralesSearch.getCodMoneda());
		
		List<LimitesGenerales> listaLimitesGenerales = new ArrayList<>();
		
		LimiteRequest limiteRequest = new LimiteRequest(); 
		limiteRequest.setIdUsuario("test");
		limiteRequest.setIdSesion("20210101121213");
		limiteRequest.setCodUsuario("E66666");
		limiteRequest.setCanal("8");
		LimitesGenerales limite = new LimitesGenerales();
		if(!limitesGeneralesSearch.getCodMoneda().equals(""))
			limite.setCodMoneda(limitesGeneralesSearch.getCodMoneda());
		limiteRequest.setLimite(limite);
		
		try {
			listaLimitesGenerales = limitesGeneralesServiceApirest.listaLimitesGenerales(limiteRequest);
			log.info("lista: "+listaLimitesGenerales.isEmpty());
			if(!listaLimitesGenerales.isEmpty()) {
				model.addAttribute("listaLimitesGenerales", listaLimitesGenerales);
	    		return "convenio/limitesGenerales/listaLimitesGenerales";
			}else {
				//redirectAttributes.addFlashAttribute("mensajeError", " Codigo : 0001 descripcion: Operacion Exitosa.La consulta no arrojo resultado.");
				model.addAttribute("listaLimitesGenerales", listaLimitesGenerales);
				model.addAttribute("mensajeError", " Codigo : 0001 descripcion: Operacion Exitosa.La consulta no arrojo resultado.");
				return "convenio/limitesGenerales/listaLimitesGenerales";
			}
			
		} catch (CustomException e) {
			
			log.error("error: "+e);
			model.addAttribute("listaLimitesGenerales", listaLimitesGenerales);
			model.addAttribute("mensajeError", e.getMessage());
			return "convenio/limitesGenerales/listaLimitesGenerales";
		}
		
		
		
	}
	
	

	@ModelAttribute
	public void setGenericos(Model model) {
		LimitesGenerales limitesGeneralesSearch = new LimitesGenerales();
		model.addAttribute("limitesGeneralesSearch", limitesGeneralesSearch);
	}
		
	
	
}
