package com.tourcan.mem.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tourcan.mem.model.MemDAO;
import com.tourcan.mem.model.MemVO;
import com.tourcan.util.ApplicationContextUtils;

@Path("members")
public class MemService {
	MemDAO dao = (MemDAO) ApplicationContextUtils.getContext().getBean("memDAO");

	// @GET
	// @Path("{id: [0-9]+}")
	// @Produces({ MediaType.APPLICATION_JSON })
	// public Response queryById(@PathParam("id") Integer id) {
	// MemVO vo;
	// if ((vo = dao.findById(id)) == null) {
	// HashMap<String, Object> msg = new HashMap<String, Object>();
	// msg.put("result", "failure");
	// msg.put("error", "id not exist.");
	// return Response.status(Status.NOT_FOUND).entity(msg).build();
	// } else {
	// // 200 OK
	// return Response.status(Status.OK).entity(vo).build();
	// }
	// }

	@GET
	@Path("{uid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryByUid(@PathParam("uid") String uid) {
		MemVO vo;
		if ((vo = dao.findByUid(uid)) == null) {
			HashMap<String, Object> msg = new HashMap<String, Object>();
			msg.put("result", "failure");
			msg.put("error", "uid not exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			return Response.status(Status.OK).entity(vo).build();
		}
	}

	@GET
	@Path("byname/{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryByName(@PathParam("name") String name) {
		List<MemVO> vos;
		if ((vos = dao.findByName(name)) == null) {
			HashMap<String, Object> msg = new HashMap<String, Object>();
			msg.put("result", "failure");
			msg.put("error", "no matched entry exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			return Response.status(Status.OK).entity(vos).build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryAll() {
		List<MemVO> vos = dao.getAll();
		if ((vos = dao.getAll()) == null) {
			HashMap<String, Object> msg = new HashMap<String, Object>();
			msg.put("result", "failure");
			msg.put("error", "no data.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			return Response.status(Status.OK).entity(vos).build();
		}
	}

	/**
	 * possible return: CONFLICT, BAD_REQUEST, CREATED and INTERNAL_SERVER_ERROR
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insertMem(MemVO vo) {
		HashMap<String, Object> msg = null;
		// for general message, such as OK, failure
		HashMap<String, String> err = new HashMap<String, String>();
		// for column-specific message, such as hotel_name is invalid.

		if (vo.getMem_uid() == null) {
			// mem_uid is provided by firebase, so it can NOT be null.
			err.put("mem_uid", "must provide.");
		} else {
			if ((dao.findByUid(vo.getMem_uid())) == null) {
				// 404 Not found - that is what we want.
				// msg = new HashMap<String, Object>();
				// msg.put("result", "failure");
				// msg.put("error", "uid not exist.");
				// return Response.status(Status.NOT_FOUND).entity(msg).build();
			} else {
				// 409 Conflict
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "id already exist.");
				return Response.status(Status.CONFLICT).entity(msg).build();
			}
		}

		// mem_lname
		if (vo.getMem_lname() == null) {
			err.put("mem_lname", "can't be empty.");
		} else {
			if (vo.getMem_lname().trim().length() == 0)
				err.put("mem_lname", "can't be empty.");
			if (vo.getMem_lname().trim().length() > 20)
				err.put("mem_lname", "too long.");
		}

		// mem_fname
		if (vo.getMem_fname() == null) {
			err.put("mem_fname", "can't be empty.");
		} else {
			if (vo.getMem_fname().trim().length() == 0)
				err.put("mem_fname", "can't be empty.");
			if (vo.getMem_fname().trim().length() > 20)
				err.put("mem_fname", "too long.");
		}

		// mem_nick
		if (vo.getMem_nick() == null) {
			// vo.setMem_nick("");
		} else {
			// if (vo.getMem_nick().trim().length() == 0)
			// err.put("mem_nick", "can't be empty.");
			if (vo.getMem_nick().trim().length() > 20)
				err.put("mem_nick", "too long.");
		}

		// mem_sex
		if (vo.getMem_sex() == null) {
			// err.put("mem_sex", "must provide.");
			// vo.setMem_sex(0);
		} else {
			// TODO handle allowed value. (ISO/IEC5218, maybe a new entity?)
			if (vo.getMem_sex() == 0 || vo.getMem_sex() == 1 || vo.getMem_sex() == 2 || vo.getMem_sex() == 9) {
			} else {
				err.put("mem_sex", "invalid input.");
			}
		}

		// region_id
		if (vo.getRegion_id() == null || vo.getRegion_id() < 0 || vo.getRegion_id() == 0)
			err.put("region_id", "must provide.");

		// mem_bdate
		if (vo.getMem_bdate() == null) {
			err.put("mem_bdate", "must provide.");
		} else {
			if (vo.getMem_bdate().after(new Date())) {
				// Welcome back, Doctor Brown.
				err.put("mem_bdate", "invalid date.");
			}
		}

		// mem_account
		// if use firebase, this column become unnecessary.
		// if (vo.getMem_account() == null) {
		// vo.setMem_account(UUID.randomUUID().toString());
		// } else {
		// if (vo.getMem_account().trim().length() == 0)
		// err.put("mem_account", "can't be empty.");
		// if (vo.getMem_account().trim().length() > 40)
		// err.put("mem_account", "too long.");
		// }

		// mem_pwd
		// if use firebase, this column become unnecessary.
		// if (vo.getMem_pwd() == null) {
		// vo.setMem_pwd(UUID.randomUUID().toString());
		// } else {
		// if (vo.getMem_pwd().trim().length() == 0)
		// err.put("mem_pwd", "can't be empty.");
		// }

		// mem_id
		// if use firebase, this column become unnecessary.
		// if (vo.getMem_id() == null) {
		// } else {
		// }

		// mem_regtime
		// register time should be the time when data inserted.
		if (vo.getMem_regtime() == null) {
			// err.put("mem_regtime", "must provide.");
			vo.setMem_regtime(new Date());
		} else {
			if (vo.getMem_regtime().after(new Date())) {
				// Welcome back, Doctor Brown.
				err.put("mem_regtime", "invalid date.");
			}
			if (vo.getMem_bdate() != null && !vo.getMem_bdate().before(vo.getMem_regtime())) {
				// data can be after, before or EQUAL
				err.put("mem_regtime", "can't be earlier than birthday.");
			}
		}

		// mem_email
		// newsletter only, nothing to do with login which handled by firebase.
		if (vo.getMem_email() == null) {
			err.put("mem_email", "can't be empty.");
		} else {
			if (vo.getMem_email().trim().length() == 0)
				err.put("mem_email", "can't be empty.");
			if (vo.getMem_email().trim().length() > 50)
				err.put("mem_email", "too long.");
		}

		// mem_mobile
		if (vo.getMem_mobile() == null) {
			vo.setMem_mobile("");
		} else {
			if (vo.getMem_mobile().trim().length() >= 50)
				err.put("mem_mobile", "too long.");
		}

		// mem_photo
		if (vo.getMem_photo() == null) {
		} else {
		}

		// update database
		if (err.size() > 0) {
			// 400 Bad request
			msg = new HashMap<String, Object>();
			msg.put("result", "validation-error");
			msg.put("error", "invalid user input.");
			msg.put("validation", err);
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		} else {
			try {
				dao.insert(vo);
				// 201 Created
				msg = new HashMap<String, Object>();
				msg.put("result", "success");
				return Response.status(Status.CREATED).entity(msg).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "insert unsuccessful.");
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
			}
		}
	}

	/**
	 * possible return: NOT_FOUND, BAD_REQUEST, OK and INTERNAL_SERVER_ERROR
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateMem(MemVO vo) {
		HashMap<String, Object> msg = null;
		// for general message, such as OK, failure
		HashMap<String, String> err = new HashMap<String, String>();
		// for column-specific message, such as hotel_name is invalid.

		if (vo.getMem_uid() == null) {
			// mem_uid is provided by firebase, so it can NOT be null.
			err.put("mem_uid", "must provide.");
		} else {
			if ((dao.findByUid(vo.getMem_uid())) == null) {
				// 404 Not found
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "uid not exist.");
				return Response.status(Status.NOT_FOUND).entity(msg).build();
			} else {
				// 409 Conflict
				// msg = new HashMap<String, Object>();
				// msg.put("result", "failure");
				// msg.put("error", "id already exist.");
				// return Response.status(Status.CONFLICT).entity(msg).build();
			}
		}

		// mem_lname
		if (vo.getMem_lname() == null) {
			err.put("mem_lname", "can't be empty.");
		} else {
			if (vo.getMem_lname().trim().length() == 0)
				err.put("mem_lname", "can't be empty.");
			if (vo.getMem_lname().trim().length() > 20)
				err.put("mem_lname", "too long.");
		}

		// mem_fname
		if (vo.getMem_fname() == null) {
			err.put("mem_fname", "can't be empty.");
		} else {
			if (vo.getMem_fname().trim().length() == 0)
				err.put("mem_fname", "can't be empty.");
			if (vo.getMem_fname().trim().length() > 20)
				err.put("mem_fname", "too long.");
		}

		// mem_nick
		if (vo.getMem_nick() == null) {
			// err.put("mem_nick", "must provide.");
			// vo.setMem_nick("");
		} else {
			// if (vo.getMem_nick().trim().length() == 0)
			// err.put("mem_nick", "can't be empty.");
			if (vo.getMem_nick().trim().length() > 20)
				err.put("mem_nick", "too long.");
		}

		// mem_sex
		if (vo.getMem_sex() == null) {
			// err.put("mem_sex", "must provide.");
			// vo.setMem_sex(0);
		} else {
			// TODO handle allowed value. (ISO/IEC5218, maybe a new entity?)
			if (vo.getMem_sex() == 0 || vo.getMem_sex() == 1 || vo.getMem_sex() == 2 || vo.getMem_sex() == 9) {
			} else {
				err.put("mem_sex", "invalid input.");
			}
		}

		// region_id
		if (vo.getRegion_id() == null || vo.getRegion_id() < 0 || vo.getRegion_id() == 0)
			err.put("region_id", "must provide.");

		// mem_bdate
		if (vo.getMem_bdate() == null) {
			err.put("mem_bdate", "must provide.");
		} else {
			if (vo.getMem_bdate().after(new Date())) {
				// Welcome back, Doctor Brown.
				err.put("mem_bdate", "invalid date.");
			}
			if (vo.getMem_regtime() != null && vo.getMem_bdate().after(vo.getMem_regtime())) {
				// Welcome back, Doctor Brown.
				err.put("mem_bdate", "can't be later than register time.");
			}
		}

		// mem_account
		// if use firebase, this column become unnecessary.
		// if (vo.getMem_account() == null) {
		// err.put("mem_account", "must provide.");
		// } else {
		// if (vo.getMem_account().trim().length() == 0)
		// err.put("mem_account", "can't be empty.");
		// if (vo.getMem_account().trim().length() > 40)
		// err.put("mem_account", "too long.");
		// }

		// mem_pwd
		// if use firebase, this column become unnecessary.
		// if (vo.getMem_pwd() == null) {
		// err.put("mem_pwd", "must provide.");
		// } else {
		// if (vo.getMem_pwd().trim().length() == 0)
		// err.put("mem_pwd", "can't be empty.");
		// }

		// mem_id
		// if use firebase, this column become unnecessary.
		// if (vo.getMem_id() == null) {
		// } else {
		// }

		// mem_regtime
		// register time should be the time when data inserted.
		if (vo.getMem_regtime() == null) {
			err.put("mem_regtime", "must provide.");
			// vo.setMem_regtime(new Date());
		} else {
			if (vo.getMem_bdate().after(new Date())) {
				err.put("mem_regtime", "invalid date.");
			}
			if (vo.getMem_bdate() != null && vo.getMem_bdate().after(vo.getMem_regtime())) {
				// Welcome back, Doctor Brown.
				err.put("mem_regtime", "can't be earlier than birthday.");
			}
		}

		// mem_email
		// newsletter only, nothing to do with login which handled by firebase.
		if (vo.getMem_email() == null) {
			err.put("mem_email", "can't be empty.");
		} else {
			if (vo.getMem_email().trim().length() == 0)
				err.put("mem_email", "can't be empty.");
			if (vo.getMem_email().trim().length() > 50)
				err.put("mem_email", "too long.");
		}

		// mem_mobile
		if (vo.getMem_mobile() == null) {
			err.put("mem_mobile", "must provide.");
		} else {
			if (vo.getMem_mobile().trim().length() >= 50)
				err.put("mem_mobile", "too long.");
		}

		// mem_photo
		if (vo.getMem_photo() == null) {
		} else {
		}

		// update database
		if (err.size() > 0) {
			// 400 Bad request
			msg = new HashMap<String, Object>();
			msg.put("result", "validation-error");
			msg.put("error", "invalid user input.");
			msg.put("validation", err);
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		} else {
			try {
				dao.update(vo);
				// 200 OK
				msg = new HashMap<String, Object>();
				msg.put("result", "success");
				return Response.status(Status.OK).entity(msg).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "update unsuccessful.");
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
			}
		}
	}

	@DELETE
	@Path("{uid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteMemByUid(@PathParam("uid") String uid) {
		HashMap<String, Object> msg = null;
		MemVO vo;
		if ((vo = dao.findByUid(uid)) == null) {
			// 404 Not found
			msg = new HashMap<String, Object>();
			msg.put("result", "failure");
			msg.put("error", "id not exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			vo = ApplicationContextUtils.getContext().getBean(MemVO.class);
			vo.setMem_uid(uid);
			try {
				dao.delete(vo);
				// 200 OK
				msg = new HashMap<String, Object>();
				msg.put("result", "success");
				return Response.status(Status.OK).entity(msg).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "delete unsuccessful.");
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
			}
		}
	}
}