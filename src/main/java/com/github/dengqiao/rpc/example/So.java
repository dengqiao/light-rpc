package com.github.dengqiao.rpc.example;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @classname: So
 * @description: 对应订单表
 * @author yanghuatao
 * @date Feb 28, 2013 12:03:27 PM
 */
public class So implements Serializable {

	private static final long serialVersionUID = 6233442771778582469L;

	/** 子订单列表 */
	private List<So> childSoList;
	/** 父订单 */
	private So parentSo;
	
	/** ID */
	private Long id;

	/** 用户ID */
	private Long endUserId;

	/** 定购金额=产品总额+运费 */
	private Double orderAmount;

	/** 订单编号 */
	private String orderCode;

	/** 订单状态 */
	private Integer orderStatus;

	/** 失败原因 {1:错误地址、2:用户不在、3:货物送错、4:货物质量问题、5:用户拒收、6:其它} */
	private Integer reasonFailure;


	/** 订单类型 {0:普通订单、1:客户升级、2:聚集订单、3:代售订单、4:订购、5:新聚集订单、6:中信验证用户、7:手机充值、8:揽货订单、9:电子礼品卡} */
	private Integer orderType;

	/** 是否需要联系客服、审核 {0:不需要、1:需要客服联系、3:需要产品部审核、9:需要产品经理审核} */
	private Long orderNeedCs;

	/** 是否需要客服 */
	private String orderRemark;

	/** 运费 */
	private Double orderDeliveryFee;

	/** 订单来源 {0:网上下单、1:电话、3:手机、4:QQ网购、5:Tmall订单、6:掌上医院订单、7:药网Tmall、8:药网店中店} */
	private Integer orderSource;

	/** 操作人ID */
	private Long backOperatorId;

	/** 用反利账号支付的钱 */
	private Double orderPaidByRebate;

	/** 付款确认日期 */
	private Date orderPaymentConfirmDate;

	/** 支付情况 {1:paid、2:unpay、3:partly paid、4:待审核} */
	private Integer orderPaymentSignal;

	/** 付款方式 */
	private Long orderPaymentMethodId;

	/** 银行或邮局的系统的流水号或支付号 */
	private String orderPaymentCode;

	/** 订单创立日期 */
	private Date orderCreateTime;

	/** 订单到物流日期 */
	private Date orderToLogisticsTime;

	/** 出库状态 {0:未出库、1:已出库} */
	private Integer orderOutOfInventoryStatus;

	/** 收货人和地址 */
	private Long goodReceiverId;

	/** 发票需要情况 {0:不需要、1:旧版普通、2:新版普通、3:增值税发票} */
	private Integer orderNeedInvoice;

	/** 父单订单ID */
	private Long parentSoId;
	
	/** 父单订单号 */
	private String parentSoCode;

	/** 预期发货时间 */
	private Date expectDeliveryDate;

	/** 实际发货时间 */
	private Date deliveryDate;

	/** 期望收货日期 */
	private Date expectReceiveDate;

	/** 期望收货时间段 {0:不限、1:上午、2:下午、3:晚上} */
	private Integer expectReceiveTime;

	/** 实际收货时间 */
	private Date receiveDate;

	/** 送货方式 */
	private Long orderDeliveryMethodId;

	/** 帐户支付的金额 */
	private Double orderPaidByAccount;

	/** 其他方式支付的金额 */
	private Double orderPaidByOthers;

	/** COD产品为0、非COD产品为(order_paid_by_account+order_padi_by_rebate+order_paid_by_others) */
	private Double accountPayable;

	/** 产品总额(各个ITEM的amount总和) */
	private Double productAmount;

	/**
	 * 数据交换标志位 {-9:后台取消订单操作、-8:前台取消订单操作、-4:状态解锁报错、-3:CopyUsertoWms异常、-2:转换异常、-1:不需要转DO、0:未转DO、2:订单锁定、1:已转DO、4:后台修改订单商品列表锁定订单、5:后台修改订单配送信息锁定订单、6:后台编辑订单收货信息锁定订单、9:前台在编辑订单锁定订单}
	 */
	private Integer dataExchangeFlag;

	/** 收货人姓名 */
	private String goodReceiverName;

	/** 收货人地址 */
	private String goodReceiverAddress;

	/** 收货人省份 */
	private String goodReceiverProvince;

	/** 收货人城市 */
	private String goodReceiverCity;

	/** 收货人地区 */
	private String goodReceiverCounty;

	/** 收货人邮编 */
	private String goodReceiverPostCode;

	/** 收货人电话 */
	private String goodReceiverPhone;

	/** 供应商处理订单状态 {10:未向供应商下单、20:已向供应商下单、30:供应商到货、40:已转PO} */
	private Integer supplierProcessStatus;

	/** 采购订单ID(后台处理代售订单和聚集订单时要填写) * */
	private Long poId;

	/** 抵用券支付 */
	private Double orderPaidByCoupon;

	/** 取消日期 */
	private Date cancelDate;

	/** 是否是叶子订单(无子订单) {1:leaf、2:not leaf} */
	private Integer isLeaf;

	/** 客服备注 */
	private String orderCsRemark;

	/** 收货人手机号 */
	private String goodReceiverMobile;

	/** 购物返利标志 */
	private Integer shoppingRebateFlag;

	/** 是否需要取消标志位 {0:不需要取消、10:需要取消、20:仓库处理中、30:仓库处理完毕} */
	private Long doCancelFlag;

	/** 卡支付金额 */
	private Double orderPaidByCard;

	/** 参考积分 */
	private Integer referencePoint;

	/** 实际积分 */
	private Integer realPoint;

	/** 补偿积分 */
	private Integer recompensePoints;

	/** 送货单公司名 */
	private String deliverySheetCompanyName;

	/** 送货单公司url */
	private String deliverySheetCompanyUrl;

	/** 应得到的万里通积分 */
	private Integer wltPoint;

	/** 是否易碎 */
	private Integer isFragile;

	/** 是否液体 */
	private Integer isLiquid;

	/** 为该用户的第几次购买 */
	private Long boughtTimes;

	/** 分期付款-合同号 */
	private String contractNo;

	/** 特殊处理标记 {0:普通订单，不需要特殊处理、1:团购虚拟出入库订单、3:(B2B欠款订单)未审核、4:(B2B欠款订单)下单审核不通过、5:(B2B欠款订单)下单审核通过、6:(B2B欠款订单)财务审核不通过} */
	private Integer specProcFlag;

	/** 导入订单来源 {0:其它、1:支付宝、2:快钱} */
	private Long orderImportSource;

	/** 收货人城市ID */
	private Long goodReceiverCityId;

	/** 收货国家ID */
	private Long goodReceiverCountryId;

	/** 收货人区县ID */
	private Long goodReceiverCountyId;

	/** 收货人省ID */
	private Long goodReceiverProvinceId;

	/** 好耶标识Uid */
	private String allyesUid;

	/** 支付网关ID */
	private Long paymentGatewayId;

	/** 是否需要调拨 {0:不需调拨、1:需要调拨} */
	private Integer needAllocation;

	/** 仓库ID */
	private Long warehouseId;

	/** 配送商编号 */
	private Long deliverySupplierid;

	/** 导入订单，是否需要先出库，后收款标志 {0:否、1:是} */
	private Integer orderImportDoFlag;

	/** 订单取消人 */
	private Long cancelOperatorId;

	/** 配送员手机 */
	private String orderDeliveryPersonMobile;

	/** 预计送达日期 */
	private Date estimateReceiveDate;

	/** 发货备注 */
	private String deliveryRemark;

	/** 收货备注 */
	private String receiveRemark;

	/** 站点类型 {1:一号店、2:药网} */
	private Long mcSiteId;

	/** 店中店订单快递单号 */
	private String merchantExpressNbr;

	/** 指定配送时间描述 */
	private String supplierDeliveryTime;

	/** 附加运费 */
	private Double orderDeliveryAddFee;

	/** IP地址 */
	private String userIp;

	/** TRACKING的GUID */
	private String userGuid;

	/** */
	private Long orderNeedIntegral;

	/** 虚拟库存状态 {0:只使用实际库存或使用了虚拟库存，但到货、1:使用了虚拟库存,但没到货} */
	private Integer virtualStockStatus;

	/** {0:前台普通订单、1:团购订单、2:EPP订单、3:处方药订单、4:B2B订单、5:店中店代售、6:平安3g标志、10:定期购订单} */
	private Integer businessType;

	/** 更新时间，自动触发 */
	private Date updateTime;

	/** 订单审核失败原因 */
	private String orderCsReason;

	/** 处理时间 */
	private Date treatmentTime;

	/** 处理人 */
	private String treatmentUserId;

	/** 是否是半日达订单 {0:否、1:是} */
	private Integer isHalfDayDelivery;

	/** 参加促销活动立减金额 */
	private Double orderPromotionDiscount;

	/** 发货单打印份数主要供企业订单使用 */
	private Integer printNum;

	/** 创建订单、支付时间，目的是点击支付后续命两小时 */
	private Date orderCreatePayTime;

	/** 配送方式类型 {10001:普通快递、20001:EMS、30001:供应商直送、40001:自提、30002:店中店商家直送} */
	private Integer deliveryMethodType;

	/** 用户选择的配送服务类型 {10001:普通快递、10002:指定收货日期、10003:一日三送、10004:半日达} */
	private Integer deliveryServiceType;

	/** 用户选择的支付服务类型 {0:账户支付、1:网上支付、2:货到付款、3:邮局汇款、4:银行转账、5:pos机、6:万里通、7:分期付款、8:合同账期、9:货到转账、10:货到付支票} */
	private Integer payServiceType;

	/** 收货人四级区域名称 */
	private String goodReceiverArea;

	/** 收货人四级区域ID */
	private Long goodReceiverAreaId;

	/** 该配送商支持的配送级别 {0:所有、1:空运、2:陆运} */
	private Integer deliveryLevel;

	/** 订单重量 */
	private Double orderWeight;

	/** 配送库区标志 0为默认值没有库区 */
	private Long deliveryPartitionId;

	/** 赠品单标识 {0:非赠品单、1:赠品单} */
	private Integer almostGiftFlag;

	/** 提前转D0天数，默认值0 */
	private Long preSo2doDays;

	/** so转do专用，货到应收金额 */
	private Double collectOnDeliveryAmount;

	/** service by yihaodian类型 1:LBY */
	private Integer sbyType;

	/** 是否需要返库存标志 {0:不需要、1:需要} */
	private Integer isNeedReturnStockFlag;

	/** 商家ID */
	private Long merchantId;

	/** 站点类型 {1:1号店、2:1mall} */
	private Integer siteType;

	/** 配货类型 {1:本地直发、2:异地直发、3:DO调拨、4:TO调拨或PO采购} */
	private Integer orderFulfillmentType;

	/** 发到终端用户的DC */
	private Long lastDcId;

	/** 核算运费 */
	private Double orderAccountingDeliveryFee;

	/** 乐观锁 */
	private Long versionNo;
	
	/** 免邮抵用券费用 */
	private Double deliveryFeePaidByCoupon;

	/** 定金支付时间 */
	private Date depositPaidTime;
	/** 定金预售订单尾款开始支付时间 */
	private Date finalStartPayTime;
	
	/** {0：未删除; 1：（回收站-预留）; 2：用户删除} */
	private Integer deleteStatus;
	
	 /**行邮税金额**/
	 private Double travelBaggageTaxAmount;
	 
	 /** 门店ID*/
	 private Long storeId;
	 
	 /** 自动收货天数*/
	 private Integer autoReceiveDays;
	 
	 /** 运费险类型*/
	 private Integer deliveryFeeInsuranceType;
	 	 
	 /** 运费险金额*/
	 private Double deliveryFeeInsuranceAmount;
	 
	 /**标识是否为历史订单。0， 近期订单；1，历史订单。*/
	 private Integer isHistory;
	 
	/**
	 * @return 子订单列表
	 */
	public List<So> getChildSoList() {
		return childSoList;
	}

	/**
	 * @param 子订单列表
	 */
	public void setChildSoList(List<So> childSoList) {
		this.childSoList = childSoList;
	}
	/**
	 * @return 父订单
	 */
	public So getParentSo() {
		return parentSo;
	}

	/**
	 * @param 父订单
	 */
	public void setParentSo(So parentSo) {
		this.parentSo = parentSo;
	}

	/**
	 * @return ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return 用户ID
	 */
	public Long getEndUserId() {
		return endUserId;
	}

	/**
	 * @param 用户ID
	 */
	public void setEndUserId(Long endUserId) {
		this.endUserId = endUserId;
	}

	/**
	 * @return 定购金额=产品总额+运费
	 */
	public Double getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param 定购金额=产品总额+运费
	 */
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * @param 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * @return 订单状态
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param 订单状态
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return 失败原因{1:错误地址、2:用户不在、3:货物送错、4:货物质量问题、5:用户拒收、6:其它}
	 */
	public Integer getReasonFailure() {
		return reasonFailure;
	}

	/**
	 * @param 失败原因{1:错误地址、2:用户不在、3:货物送错、4:货物质量问题、5:用户拒收、6:其它}
	 */
	public void setReasonFailure(Integer reasonFailure) {
		this.reasonFailure = reasonFailure;
	}


	/**
	 * @return 订单类型{0:普通订单、1:客户升级、2:聚集订单、3:代售订单、4:订购、5:新聚集订单、6:中信验证用户、7:手机充值、8:揽货订单、9:电子礼品卡}
	 */
	public Integer getOrderType() {
		return orderType;
	}

	/**
	 * @param 订单类型{0:普通订单、1:客户升级、2:聚集订单、3:代售订单、4:订购、5:新聚集订单、6:中信验证用户、7:手机充值、8:揽货订单、9:电子礼品卡}
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return 是否需要联系客服、审核 {0:不需要、1:需要客服联系、3:需要产品部审核、9:需要产品经理审核}
	 */
	public Long getOrderNeedCs() {
		return orderNeedCs;
	}

	/**
	 * @param 是否需要联系客服、审核 {0:不需要、1:需要客服联系、3:需要产品部审核、9:需要产品经理审核}
	 */
	public void setOrderNeedCs(Long orderNeedCs) {
		this.orderNeedCs = orderNeedCs;
	}

	/**
	 * @return 是否需要客服
	 */
	public String getOrderRemark() {
		return orderRemark;
	}

	/**
	 * @param 是否需要客服
	 */
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	/**
	 * @return 运费
	 */
	public Double getOrderDeliveryFee() {
		return orderDeliveryFee;
	}

	/**
	 * @param 运费
	 */
	public void setOrderDeliveryFee(Double orderDeliveryFee) {
		this.orderDeliveryFee = orderDeliveryFee;
	}

	/**
	 * @return 订单来源{0:网上下单、1:电话、3:手机、4:QQ网购、5:Tmall订单、6:掌上医院订单、7:药网Tmall、8:药网店中店}
	 */
	public Integer getOrderSource() {
		return orderSource;
	}

	/**
	 * @param 订单来源{0:网上下单、1:电话、3:手机、4:QQ网购、5:Tmall订单、6:掌上医院订单、7:药网Tmall、8:药网店中店}
	 */
	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	/**
	 * @return 操作人ID
	 */
	public Long getBackOperatorId() {
		return backOperatorId;
	}

	/**
	 * @param 操作人ID
	 */
	public void setBackOperatorId(Long backOperatorId) {
		this.backOperatorId = backOperatorId;
	}

	/**
	 * @return 用反利账号支付的钱
	 */
	public Double getOrderPaidByRebate() {
		return orderPaidByRebate;
	}

	/**
	 * @param 用反利账号支付的钱
	 */
	public void setOrderPaidByRebate(Double orderPaidByRebate) {
		this.orderPaidByRebate = orderPaidByRebate;
	}

	/**
	 * @return 付款确认日期
	 */
	public Date getOrderPaymentConfirmDate() {
		return orderPaymentConfirmDate;
	}

	/**
	 * @param 付款确认日期
	 */
	public void setOrderPaymentConfirmDate(Date orderPaymentConfirmDate) {
		this.orderPaymentConfirmDate = orderPaymentConfirmDate;
	}

	/**
	 * @return 支付情况{1:paid、2:unpay、3:partlypaid、4:待审核}
	 */
	public Integer getOrderPaymentSignal() {
		return orderPaymentSignal;
	}

	/**
	 * @param 支付情况{1:paid、2:unpay、3:partlypaid、4:待审核}
	 */
	public void setOrderPaymentSignal(Integer orderPaymentSignal) {
		this.orderPaymentSignal = orderPaymentSignal;
	}

	/**
	 * @return 付款方式
	 */
	public Long getOrderPaymentMethodId() {
		return orderPaymentMethodId;
	}

	/**
	 * @param 付款方式
	 */
	public void setOrderPaymentMethodId(Long orderPaymentMethodId) {
		this.orderPaymentMethodId = orderPaymentMethodId;
	}

	/**
	 * @return 银行或邮局的系统的流水号或支付号
	 */
	public String getOrderPaymentCode() {
		return orderPaymentCode;
	}

	/**
	 * @param 银行或邮局的系统的流水号或支付号
	 */
	public void setOrderPaymentCode(String orderPaymentCode) {
		this.orderPaymentCode = orderPaymentCode;
	}

	/**
	 * @return 订单创立日期
	 */
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	/**
	 * @param 订单创立日期
	 */
	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	/**
	 * @return 订单到物流日期
	 */
	public Date getOrderToLogisticsTime() {
		return orderToLogisticsTime;
	}

	/**
	 * @param 订单到物流日期
	 */
	public void setOrderToLogisticsTime(Date orderToLogisticsTime) {
		this.orderToLogisticsTime = orderToLogisticsTime;
	}

	/**
	 * @return 出库状态{0:未出库、1:已出库}
	 */
	public Integer getOrderOutOfInventoryStatus() {
		return orderOutOfInventoryStatus;
	}

	/**
	 * @param 出库状态{0:未出库、1:已出库}
	 */
	public void setOrderOutOfInventoryStatus(Integer orderOutOfInventoryStatus) {
		this.orderOutOfInventoryStatus = orderOutOfInventoryStatus;
	}

	/**
	 * @return 收货人和地址
	 */
	public Long getGoodReceiverId() {
		return goodReceiverId;
	}

	/**
	 * @param 收货人和地址
	 */
	public void setGoodReceiverId(Long goodReceiverId) {
		this.goodReceiverId = goodReceiverId;
	}

	/**
	 * @return 发票需要情况{0:不需要、1:旧版普通、2:新版普通、3:增值税发票}
	 */
	public Integer getOrderNeedInvoice() {
		return orderNeedInvoice;
	}

	/**
	 * @param 发票需要情况{0:不需要、1:旧版普通、2:新版普通、3:增值税发票}
	 */
	public void setOrderNeedInvoice(Integer orderNeedInvoice) {
		this.orderNeedInvoice = orderNeedInvoice;
	}

	/**
	 * @return 父单订单ID
	 */
	public Long getParentSoId() {
		return parentSoId;
	}

	/**
	 * @param 父单订单ID
	 */
	public void setParentSoId(Long parentSoId) {
		this.parentSoId = parentSoId;
	}

	/**
	 * @return 预期发货时间
	 */
	public Date getExpectDeliveryDate() {
		return expectDeliveryDate;
	}

	/**
	 * @param 预期发货时间
	 */
	public void setExpectDeliveryDate(Date expectDeliveryDate) {
		this.expectDeliveryDate = expectDeliveryDate;
	}

	/**
	 * @return 实际发货时间
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param 实际发货时间
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return 期望收货日期
	 */
	public Date getExpectReceiveDate() {
		return expectReceiveDate;
	}

	/**
	 * @param 期望收货日期
	 */
	public void setExpectReceiveDate(Date expectReceiveDate) {
		this.expectReceiveDate = expectReceiveDate;
	}

	/**
	 * @return 期望收货时间段{0:不限、1:上午、2:下午、3:晚上}
	 */
	public Integer getExpectReceiveTime() {
		return expectReceiveTime;
	}

	/**
	 * @param 期望收货时间段{0:不限、1:上午、2:下午、3:晚上}
	 */
	public void setExpectReceiveTime(Integer expectReceiveTime) {
		this.expectReceiveTime = expectReceiveTime;
	}

	/**
	 * @return 实际收货时间
	 */
	public Date getReceiveDate() {
		return receiveDate;
	}

	/**
	 * @param 实际收货时间
	 */
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * @return 送货方式
	 */
	public Long getOrderDeliveryMethodId() {
		return orderDeliveryMethodId;
	}

	/**
	 * @param 送货方式
	 */
	public void setOrderDeliveryMethodId(Long orderDeliveryMethodId) {
		this.orderDeliveryMethodId = orderDeliveryMethodId;
	}

	/**
	 * @return 帐户支付的金额
	 */
	public Double getOrderPaidByAccount() {
		return orderPaidByAccount;
	}

	/**
	 * @param 帐户支付的金额
	 */
	public void setOrderPaidByAccount(Double orderPaidByAccount) {
		this.orderPaidByAccount = orderPaidByAccount;
	}

	/**
	 * @return 其他方式支付的金额
	 */
	public Double getOrderPaidByOthers() {
		return orderPaidByOthers;
	}

	/**
	 * @param 其他方式支付的金额
	 */
	public void setOrderPaidByOthers(Double orderPaidByOthers) {
		this.orderPaidByOthers = orderPaidByOthers;
	}

	/**
	 * @return COD产品为0、非COD产品为(order_paid_by_account+order_padi_by_rebate+order_paid_by_others)
	 */
	public Double getAccountPayable() {
		return accountPayable;
	}

	/**
	 * @param COD产品为0、非COD产品为(order_paid_by_account+order_padi_by_rebate+order_paid_by_others)
	 */
	public void setAccountPayable(Double accountPayable) {
		this.accountPayable = accountPayable;
	}

	/**
	 * @return 产品总额(各个ITEM的amount总和)
	 */
	public Double getProductAmount() {
		return productAmount;
	}

	/**
	 * @param 产品总额(各个ITEM的amount总和)
	 */
	public void setProductAmount(Double productAmount) {
		this.productAmount = productAmount;
	}


	/**
	 * @return 数据交换标志位{-9:后台取消订单操作、-8:前台取消订单操作、-4:状态解锁报错、-3:CopyUsertoWms异常、-2:转换异常、-1:不需要转DO、0:未转DO、2:订单锁定、1:已转DO、4:后台修改订单商品列表锁定订单、5:后台修改订单配送信息锁定订单、6:后台编辑订单收货信息锁定订单、9:前台在编辑订单锁定订单}
	 */
	public Integer getDataExchangeFlag() {
		return dataExchangeFlag;
	}

	/**
	 * @param 数据交换标志位{-9:后台取消订单操作、-8:前台取消订单操作、-4:状态解锁报错、-3:CopyUsertoWms异常、-2:转换异常、-1:不需要转DO、0:未转DO、2:订单锁定、1:已转DO、4:后台修改订单商品列表锁定订单、5:后台修改订单配送信息锁定订单、6:后台编辑订单收货信息锁定订单、9:前台在编辑订单锁定订单}
	 */
	public void setDataExchangeFlag(Integer dataExchangeFlag) {
		this.dataExchangeFlag = dataExchangeFlag;
	}

	/**
	 * @return 收货人姓名
	 */
	public String getGoodReceiverName() {
		return goodReceiverName;
	}

	/**
	 * @param 收货人姓名
	 */
	public void setGoodReceiverName(String goodReceiverName) {
		this.goodReceiverName = goodReceiverName;
	}

	/**
	 * @return 收货人地址
	 */
	public String getGoodReceiverAddress() {
		return goodReceiverAddress;
	}

	/**
	 * @param 收货人地址
	 */
	public void setGoodReceiverAddress(String goodReceiverAddress) {
		this.goodReceiverAddress = goodReceiverAddress;
	}

	/**
	 * @return 收货人省份
	 */
	public String getGoodReceiverProvince() {
		return goodReceiverProvince;
	}

	/**
	 * @param 收货人省份
	 */
	public void setGoodReceiverProvince(String goodReceiverProvince) {
		this.goodReceiverProvince = goodReceiverProvince;
	}

	/**
	 * @return 收货人城市
	 */
	public String getGoodReceiverCity() {
		return goodReceiverCity;
	}

	/**
	 * @param 收货人城市
	 */
	public void setGoodReceiverCity(String goodReceiverCity) {
		this.goodReceiverCity = goodReceiverCity;
	}

	/**
	 * @return 收货人地区
	 */
	public String getGoodReceiverCounty() {
		return goodReceiverCounty;
	}

	/**
	 * @param 收货人地区
	 */
	public void setGoodReceiverCounty(String goodReceiverCounty) {
		this.goodReceiverCounty = goodReceiverCounty;
	}

	/**
	 * @return 收货人邮编
	 */
	public String getGoodReceiverPostCode() {
		return goodReceiverPostCode;
	}

	/**
	 * @param 收货人邮编
	 */
	public void setGoodReceiverPostCode(String goodReceiverPostCode) {
		this.goodReceiverPostCode = goodReceiverPostCode;
	}

	/**
	 * @return 收货人电话
	 */
	public String getGoodReceiverPhone() {
		return goodReceiverPhone;
	}

	/**
	 * @param 收货人电话
	 */
	public void setGoodReceiverPhone(String goodReceiverPhone) {
		this.goodReceiverPhone = goodReceiverPhone;
	}

	/**
	 * @return 供应商处理订单状态{10:未向供应商下单、20:已向供应商下单、30:供应商到货、40:已转PO}
	 */
	public Integer getSupplierProcessStatus() {
		return supplierProcessStatus;
	}

	/**
	 * @param 供应商处理订单状态{10:未向供应商下单、20:已向供应商下单、30:供应商到货、40:已转PO}
	 */
	public void setSupplierProcessStatus(Integer supplierProcessStatus) {
		this.supplierProcessStatus = supplierProcessStatus;
	}

	/**
	 * @return 采购订单ID(后台处理代售订单和聚集订单时要填写)
	 */
	public Long getPoId() {
		return poId;
	}

	/**
	 * @param 采购订单ID(后台处理代售订单和聚集订单时要填写)
	 */
	public void setPoId(Long poId) {
		this.poId = poId;
	}

	/**
	 * @return 抵用券支付
	 */
	public Double getOrderPaidByCoupon() {
		return orderPaidByCoupon;
	}

	/**
	 * @param 抵用券支付
	 */
	public void setOrderPaidByCoupon(Double orderPaidByCoupon) {
		this.orderPaidByCoupon = orderPaidByCoupon;
	}

	/**
	 * @return 取消日期
	 */
	public Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * @param 取消日期
	 */
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	/**
	 * @return 是否是叶子订单(无子订单){1:leaf、2:notleaf}
	 */
	public Integer getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param 是否是叶子订单(无子订单){1:leaf、2:notleaf}
	 */
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return 客服备注
	 */
	public String getOrderCsRemark() {
		return orderCsRemark;
	}

	/**
	 * @param 客服备注
	 */
	public void setOrderCsRemark(String orderCsRemark) {
		this.orderCsRemark = orderCsRemark;
	}


	/**
	 * @return 收货人手机号
	 */
	public String getGoodReceiverMobile() {
		return goodReceiverMobile;
	}

	/**
	 * @param 收货人手机号
	 */
	public void setGoodReceiverMobile(String goodReceiverMobile) {
		this.goodReceiverMobile = goodReceiverMobile;
	}

	/**
	 * @return 购物返利标志
	 */
	public Integer getShoppingRebateFlag() {
		return shoppingRebateFlag;
	}

	/**
	 * @param 购物返利标志
	 */
	public void setShoppingRebateFlag(Integer shoppingRebateFlag) {
		this.shoppingRebateFlag = shoppingRebateFlag;
	}

	/**
	 * @return 是否需要取消标志位{0:不需要取消、10:需要取消、20:仓库处理中、30:仓库处理完毕}
	 */
	public Long getDoCancelFlag() {
		return doCancelFlag;
	}

	/**
	 * @param 是否需要取消标志位{0:不需要取消、10:需要取消、20:仓库处理中、30:仓库处理完毕}
	 */
	public void setDoCancelFlag(Long doCancelFlag) {
		this.doCancelFlag = doCancelFlag;
	}

	/**
	 * @return 卡支付金额
	 */
	public Double getOrderPaidByCard() {
		return orderPaidByCard;
	}

	/**
	 * @param 卡支付金额
	 */
	public void setOrderPaidByCard(Double orderPaidByCard) {
		this.orderPaidByCard = orderPaidByCard;
	}

	/**
	 * @return 参考积分
	 */
	public Integer getReferencePoint() {
		return referencePoint;
	}

	/**
	 * @param 参考积分
	 */
	public void setReferencePoint(Integer referencePoint) {
		this.referencePoint = referencePoint;
	}

	/**
	 * @return 实际积分
	 */
	public Integer getRealPoint() {
		return realPoint;
	}

	/**
	 * @param 实际积分
	 */
	public void setRealPoint(Integer realPoint) {
		this.realPoint = realPoint;
	}

	/**
	 * @return 补偿积分
	 */
	public Integer getRecompensePoints() {
		return recompensePoints;
	}

	/**
	 * @param 补偿积分
	 */
	public void setRecompensePoints(Integer recompensePoints) {
		this.recompensePoints = recompensePoints;
	}

	/**
	 * @return 送货单公司名
	 */
	public String getDeliverySheetCompanyName() {
		return deliverySheetCompanyName;
	}

	/**
	 * @param 送货单公司名
	 */
	public void setDeliverySheetCompanyName(String deliverySheetCompanyName) {
		this.deliverySheetCompanyName = deliverySheetCompanyName;
	}

	/**
	 * @return 送货单公司url
	 */
	public String getDeliverySheetCompanyUrl() {
		return deliverySheetCompanyUrl;
	}

	/**
	 * @param 送货单公司url
	 */
	public void setDeliverySheetCompanyUrl(String deliverySheetCompanyUrl) {
		this.deliverySheetCompanyUrl = deliverySheetCompanyUrl;
	}

	/**
	 * @return 应得到的万里通积分
	 */
	public Integer getWltPoint() {
		return wltPoint;
	}

	/**
	 * @param 应得到的万里通积分
	 */
	public void setWltPoint(Integer wltPoint) {
		this.wltPoint = wltPoint;
	}

	/**
	 * @return 是否易碎
	 */
	public Integer getIsFragile() {
		return isFragile;
	}

	/**
	 * @param 是否易碎
	 */
	public void setIsFragile(Integer isFragile) {
		this.isFragile = isFragile;
	}

	/**
	 * @return 是否液体
	 */
	public Integer getIsLiquid() {
		return isLiquid;
	}

	/**
	 * @param 是否液体
	 */
	public void setIsLiquid(Integer isLiquid) {
		this.isLiquid = isLiquid;
	}

	/**
	 * @return 为该用户的第几次购买
	 */
	public Long getBoughtTimes() {
		return boughtTimes;
	}

	/**
	 * @param 为该用户的第几次购买
	 */
	public void setBoughtTimes(Long boughtTimes) {
		this.boughtTimes = boughtTimes;
	}

	/**
	 * @return 分期付款-合同号
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param 分期付款-合同号
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return 特殊处理标记{0:普通订单，不需要特殊处理、1:团购虚拟出入库订单、3:(B2B欠款订单)未审核、4:(B2B欠款订单)下单审核不通过、5:(B2B欠款订单)下单审核通过、6:(B2B欠款订单)财务审核不通过}
	 */
	public Integer getSpecProcFlag() {
		return specProcFlag;
	}

	/**
	 * @param 特殊处理标记{0:普通订单，不需要特殊处理、1:团购虚拟出入库订单、3:(B2B欠款订单)未审核、4:(B2B欠款订单)下单审核不通过、5:(B2B欠款订单)下单审核通过、6:(B2B欠款订单)财务审核不通过}
	 */
	public void setSpecProcFlag(Integer specProcFlag) {
		this.specProcFlag = specProcFlag;
	}

	/**
	 * @return 导入订单来源{0:其它、1:支付宝、2:快钱}
	 */
	public Long getOrderImportSource() {
		return orderImportSource;
	}

	/**
	 * @param 导入订单来源{0:其它、1:支付宝、2:快钱}
	 */
	public void setOrderImportSource(Long orderImportSource) {
		this.orderImportSource = orderImportSource;
	}

	/**
	 * @return 收货人城市ID
	 */
	public Long getGoodReceiverCityId() {
		return goodReceiverCityId;
	}

	/**
	 * @param 收货人城市ID
	 */
	public void setGoodReceiverCityId(Long goodReceiverCityId) {
		this.goodReceiverCityId = goodReceiverCityId;
	}

	/**
	 * @return 收货国家ID
	 */
	public Long getGoodReceiverCountryId() {
		return goodReceiverCountryId;
	}

	/**
	 * @param 收货国家ID
	 */
	public void setGoodReceiverCountryId(Long goodReceiverCountryId) {
		this.goodReceiverCountryId = goodReceiverCountryId;
	}

	/**
	 * @return 收货人区县ID
	 */
	public Long getGoodReceiverCountyId() {
		return goodReceiverCountyId;
	}

	/**
	 * @param 收货人区县ID
	 */
	public void setGoodReceiverCountyId(Long goodReceiverCountyId) {
		this.goodReceiverCountyId = goodReceiverCountyId;
	}

	/**
	 * @return 收货人省ID
	 */
	public Long getGoodReceiverProvinceId() {
		return goodReceiverProvinceId;
	}

	/**
	 * @param 收货人省ID
	 */
	public void setGoodReceiverProvinceId(Long goodReceiverProvinceId) {
		this.goodReceiverProvinceId = goodReceiverProvinceId;
	}

	/**
	 * @return 好耶标识Uid
	 */
	public String getAllyesUid() {
		return allyesUid;
	}

	/**
	 * @param 好耶标识Uid
	 */
	public void setAllyesUid(String allyesUid) {
		this.allyesUid = allyesUid;
	}

	/**
	 * @return 支付网关ID
	 */
	public Long getPaymentGatewayId() {
		return paymentGatewayId;
	}

	/**
	 * @param 支付网关ID
	 */
	public void setPaymentGatewayId(Long paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}

	/**
	 * @return 是否需要调拨{0:不需调拨、1:需要调拨}
	 */
	public Integer getNeedAllocation() {
		return needAllocation;
	}

	/**
	 * @param 是否需要调拨{0:不需调拨、1:需要调拨}
	 */
	public void setNeedAllocation(Integer needAllocation) {
		this.needAllocation = needAllocation;
	}

	/**
	 * @return 仓库ID
	 */
	public Long getWarehouseId() {
		return warehouseId;
	}

	/**
	 * @param 仓库ID
	 */
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * @return 配送商编号
	 */
	public Long getDeliverySupplierid() {
		return deliverySupplierid;
	}

	/**
	 * @param 配送商编号
	 */
	public void setDeliverySupplierid(Long deliverySupplierid) {
		this.deliverySupplierid = deliverySupplierid;
	}

	/**
	 * @return 导入订单，是否需要先出库，后收款标志{0:否、1:是}
	 */
	public Integer getOrderImportDoFlag() {
		return orderImportDoFlag;
	}

	/**
	 * @param 导入订单，是否需要先出库，后收款标志{0:否、1:是}
	 */
	public void setOrderImportDoFlag(Integer orderImportDoFlag) {
		this.orderImportDoFlag = orderImportDoFlag;
	}

	/**
	 * @return 订单取消人
	 */
	public Long getCancelOperatorId() {
		return cancelOperatorId;
	}

	/**
	 * @param 订单取消人
	 */
	public void setCancelOperatorId(Long cancelOperatorId) {
		this.cancelOperatorId = cancelOperatorId;
	}

	/**
	 * @return 配送员手机
	 */
	public String getOrderDeliveryPersonMobile() {
		return orderDeliveryPersonMobile;
	}

	/**
	 * @param 配送员手机
	 */
	public void setOrderDeliveryPersonMobile(String orderDeliveryPersonMobile) {
		this.orderDeliveryPersonMobile = orderDeliveryPersonMobile;
	}

	/**
	 * @return 预计送达日期
	 */
	public Date getEstimateReceiveDate() {
		return estimateReceiveDate;
	}

	/**
	 * @param 预计送达日期
	 */
	public void setEstimateReceiveDate(Date estimateReceiveDate) {
		this.estimateReceiveDate = estimateReceiveDate;
	}

	/**
	 * @return 发货备注
	 */
	public String getDeliveryRemark() {
		return deliveryRemark;
	}

	/**
	 * @param 发货备注
	 */
	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
	}

	/**
	 * @return 收货备注
	 */
	public String getReceiveRemark() {
		return receiveRemark;
	}

	/**
	 * @param 收货备注
	 */
	public void setReceiveRemark(String receiveRemark) {
		this.receiveRemark = receiveRemark;
	}

	/**
	 * @return 站点类型{1:一号店、2:药网}
	 */
	public Long getMcSiteId() {
		return mcSiteId;
	}

	/**
	 * @param 站点类型{1:一号店、2:药网}
	 */
	public void setMcSiteId(Long mcSiteId) {
		this.mcSiteId = mcSiteId;
	}

	/**
	 * @return 店中店订单快递单号
	 */
	public String getMerchantExpressNbr() {
		return merchantExpressNbr;
	}

	/**
	 * @param 店中店订单快递单号
	 */
	public void setMerchantExpressNbr(String merchantExpressNbr) {
		this.merchantExpressNbr = merchantExpressNbr;
	}

	/**
	 * @return 指定配送时间描述
	 */
	public String getSupplierDeliveryTime() {
		return supplierDeliveryTime;
	}

	/**
	 * @param 指定配送时间描述
	 */
	public void setSupplierDeliveryTime(String supplierDeliveryTime) {
		this.supplierDeliveryTime = supplierDeliveryTime;
	}

	/**
	 * @return 附加运费
	 */
	public Double getOrderDeliveryAddFee() {
		return orderDeliveryAddFee;
	}

	/**
	 * @param 附加运费
	 */
	public void setOrderDeliveryAddFee(Double orderDeliveryAddFee) {
		this.orderDeliveryAddFee = orderDeliveryAddFee;
	}

	/**
	 * @return IP地址
	 */
	public String getUserIp() {
		return userIp;
	}

	/**
	 * @param IP地址
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	/**
	 * @return TRACKING的GUID
	 */
	public String getUserGuid() {
		return userGuid;
	}

	/**
	 * @param TRACKING的GUID
	 */
	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	/**
	 * @return
	 */
	public Long getOrderNeedIntegral() {
		return orderNeedIntegral;
	}

	/**
	 * @param
	 */
	public void setOrderNeedIntegral(Long orderNeedIntegral) {
		this.orderNeedIntegral = orderNeedIntegral;
	}

	/**
	 * @return 虚拟库存状态{0:只使用实际库存或使用了虚拟库存，但到货、1:使用了虚拟库存但没到货}
	 */
	public Integer getVirtualStockStatus() {
		return virtualStockStatus;
	}

	/**
	 * @param 虚拟库存状态{0:只使用实际库存或使用了虚拟库存，但到货、1:使用了虚拟库存但没到货}
	 */
	public void setVirtualStockStatus(Integer virtualStockStatus) {
		this.virtualStockStatus = virtualStockStatus;
	}

	/**
	 * @return {0:前台普通订单、1:团购订单、2:EPP订单、3:处方药订单、4:B2B订单、5:店中店代售、6:平安3g标志、10:定期购订单}
	 */
	public Integer getBusinessType() {
		return businessType;
	}

	/**
	 * @param {0:前台普通订单、1:团购订单、2:EPP订单、3:处方药订单、4:B2B订单、5:店中店代售、6:平安3g标志、10:定期购订单}
	 */
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	/**
	 * @return 更新时间，自动触发
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param 更新时间，自动触发
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return 订单审核失败原因
	 */
	public String getOrderCsReason() {
		return orderCsReason;
	}

	/**
	 * @param 订单审核失败原因
	 */
	public void setOrderCsReason(String orderCsReason) {
		this.orderCsReason = orderCsReason;
	}

	/**
	 * @return 处理时间
	 */
	public Date getTreatmentTime() {
		return treatmentTime;
	}

	/**
	 * @param 处理时间
	 */
	public void setTreatmentTime(Date treatmentTime) {
		this.treatmentTime = treatmentTime;
	}

	/**
	 * @return 处理人
	 */
	public String getTreatmentUserId() {
		return treatmentUserId;
	}

	/**
	 * @param 处理人
	 */
	public void setTreatmentUserId(String treatmentUserId) {
		this.treatmentUserId = treatmentUserId;
	}

	/**
	 * @return 是否是半日达订单{0:否、1:是}
	 */
	public Integer getIsHalfDayDelivery() {
		return isHalfDayDelivery;
	}

	/**
	 * @param 是否是半日达订单{0:否、1:是}
	 */
	public void setIsHalfDayDelivery(Integer isHalfDayDelivery) {
		this.isHalfDayDelivery = isHalfDayDelivery;
	}

	/**
	 * @return 参加促销活动立减金额
	 */
	public Double getOrderPromotionDiscount() {
		return orderPromotionDiscount;
	}

	/**
	 * @param 参加促销活动立减金额
	 */
	public void setOrderPromotionDiscount(Double orderPromotionDiscount) {
		this.orderPromotionDiscount = orderPromotionDiscount;
	}

	/**
	 * @return 发货单打印份数主要供企业订单使用
	 */
	public Integer getPrintNum() {
		return printNum;
	}

	/**
	 * @param 发货单打印份数主要供企业订单使用
	 */
	public void setPrintNum(Integer printNum) {
		this.printNum = printNum;
	}

	/**
	 * @return 创建订单、支付时间，目的是点击支付后续命两小时
	 */
	public Date getOrderCreatePayTime() {
		return orderCreatePayTime;
	}

	/**
	 * @param 创建订单、支付时间，目的是点击支付后续命两小时
	 */
	public void setOrderCreatePayTime(Date orderCreatePayTime) {
		this.orderCreatePayTime = orderCreatePayTime;
	}

	/**
	 * @return 配送方式类型{10001:普通快递、20001:EMS、30001:供应商直送、40001:自提、30002:店中店商家直送}
	 */
	public Integer getDeliveryMethodType() {
		return deliveryMethodType;
	}

	/**
	 * @param 配送方式类型{10001:普通快递、20001:EMS、30001:供应商直送、40001:自提、30002:店中店商家直送}
	 */
	public void setDeliveryMethodType(Integer deliveryMethodType) {
		this.deliveryMethodType = deliveryMethodType;
	}

	/**
	 * @return 用户选择的配送服务类型{10001:普通快递、10002:指定收货日期、10003:一日三送、10004:半日达}
	 */
	public Integer getDeliveryServiceType() {
		return deliveryServiceType;
	}

	/**
	 * @param 用户选择的配送服务类型{10001:普通快递、10002:指定收货日期、10003:一日三送、10004:半日达}
	 */
	public void setDeliveryServiceType(Integer deliveryServiceType) {
		this.deliveryServiceType = deliveryServiceType;
	}

	/**
	 * @return 用户选择的支付服务类型{0:账户支付、1:网上支付、2:货到付款、3:邮局汇款、4:银行转账、5:pos机、6:万里通、7:分期付款、8:合同账期、9:货到转账、10:货到付支票}
	 */
	public Integer getPayServiceType() {
		return payServiceType;
	}

	/**
	 * @param 用户选择的支付服务类型{0:账户支付、1:网上支付、2:货到付款、3:邮局汇款、4:银行转账、5:pos机、6:万里通、7:分期付款、8:合同账期、9:货到转账、10:货到付支票}
	 */
	public void setPayServiceType(Integer payServiceType) {
		this.payServiceType = payServiceType;
	}

	/**
	 * @return 收货人四级区域名称
	 */
	public String getGoodReceiverArea() {
		return goodReceiverArea;
	}

	/**
	 * @param 收货人四级区域名称
	 */
	public void setGoodReceiverArea(String goodReceiverArea) {
		this.goodReceiverArea = goodReceiverArea;
	}

	/**
	 * @return 收货人四级区域ID
	 */
	public Long getGoodReceiverAreaId() {
		return goodReceiverAreaId;
	}

	/**
	 * @param 收货人四级区域ID
	 */
	public void setGoodReceiverAreaId(Long goodReceiverAreaId) {
		this.goodReceiverAreaId = goodReceiverAreaId;
	}

	/**
	 * @return 该配送商支持的配送级别{0:所有、1:空运、2:陆运}
	 */
	public Integer getDeliveryLevel() {
		return deliveryLevel;
	}

	/**
	 * @param 该配送商支持的配送级别{0:所有、1:空运、2:陆运}
	 */
	public void setDeliveryLevel(Integer deliveryLevel) {
		this.deliveryLevel = deliveryLevel;
	}

	/**
	 * @return 订单重量
	 */
	public Double getOrderWeight() {
		return orderWeight;
	}

	/**
	 * @param 订单重量
	 */
	public void setOrderWeight(Double orderWeight) {
		this.orderWeight = orderWeight;
	}

	/**
	 * @return 配送库区标志0为默认值没有库区
	 */
	public Long getDeliveryPartitionId() {
		return deliveryPartitionId;
	}

	/**
	 * @param 配送库区标志0为默认值没有库区
	 */
	public void setDeliveryPartitionId(Long deliveryPartitionId) {
		this.deliveryPartitionId = deliveryPartitionId;
	}

	/**
	 * @return 赠品单标识{0:非赠品单、1:赠品单}
	 */
	public Integer getAlmostGiftFlag() {
		return almostGiftFlag;
	}

	/**
	 * @param 赠品单标识{0:非赠品单、1:赠品单}
	 */
	public void setAlmostGiftFlag(Integer almostGiftFlag) {
		this.almostGiftFlag = almostGiftFlag;
	}

	/**
	 * @return 提前转D0天数，默认值0
	 */
	public Long getPreSo2doDays() {
		return preSo2doDays;
	}

	/**
	 * @param 提前转D0天数，默认值0
	 */
	public void setPreSo2doDays(Long preSo2doDays) {
		this.preSo2doDays = preSo2doDays;
	}

	/**
	 * @return so转do专用，货到应收金额
	 */
	public Double getCollectOnDeliveryAmount() {
		return collectOnDeliveryAmount;
	}

	/**
	 * @param so转do专用，货到应收金额
	 */
	public void setCollectOnDeliveryAmount(Double collectOnDeliveryAmount) {
		this.collectOnDeliveryAmount = collectOnDeliveryAmount;
	}

	/**
	 * @return servicebyyihaodian类型1:LBY
	 */
	public Integer getSbyType() {
		return sbyType;
	}

	/**
	 * @param servicebyyihaodian类型1:LBY
	 */
	public void setSbyType(Integer sbyType) {
		this.sbyType = sbyType;
	}

	/**
	 * @return 是否需要返库存标志{0:不需要、1:需要}
	 */
	public Integer getIsNeedReturnStockFlag() {
		return isNeedReturnStockFlag;
	}

	/**
	 * @param 是否需要返库存标志{0:不需要、1:需要}
	 */
	public void setIsNeedReturnStockFlag(Integer isNeedReturnStockFlag) {
		this.isNeedReturnStockFlag = isNeedReturnStockFlag;
	}

	/**
	 * @return 商家ID
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * @param 商家ID
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return 站点类型{1:1号店、2:1mall}
	 */
	public Integer getSiteType() {
		return siteType;
	}

	/**
	 * @param 站点类型{1:1号店、2:1mall}
	 */
	public void setSiteType(Integer siteType) {
		this.siteType = siteType;
	}

	/**
	 * @return 配货类型{1:本地直发、2:异地直发、3:DO调拨、4:TO调拨或PO采购}
	 */
	public Integer getOrderFulfillmentType() {
		return orderFulfillmentType;
	}

	/**
	 * @param 配货类型{1:本地直发、2:异地直发、3:DO调拨、4:TO调拨或PO采购}
	 */
	public void setOrderFulfillmentType(Integer orderFulfillmentType) {
		this.orderFulfillmentType = orderFulfillmentType;
	}

	/**
	 * @return 发到终端用户的DC
	 */
	public Long getLastDcId() {
		return lastDcId;
	}

	/**
	 * @param 发到终端用户的DC
	 */
	public void setLastDcId(Long lastDcId) {
		this.lastDcId = lastDcId;
	}

	/**
	 * @return 核算运费
	 */
	public Double getOrderAccountingDeliveryFee() {
		return orderAccountingDeliveryFee;
	}

	/**
	 * @param 核算运费
	 */
	public void setOrderAccountingDeliveryFee(Double orderAccountingDeliveryFee) {
		this.orderAccountingDeliveryFee = orderAccountingDeliveryFee;
	}

	/**
	 * @return 乐观锁
	 */
	public Long getVersionNo() {
		return versionNo;
	}

	/**
	 * @param 乐观锁
	 */
	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * 获取免邮抵用券费用
	 * @return deliveryFeePaidByCoupon 免邮抵用券费用
	 */
	public Double getDeliveryFeePaidByCoupon() {
		return deliveryFeePaidByCoupon;
	}

	/**
	 * 设置免邮抵用券费用
	 * @param deliveryFeePaidByCoupon 免邮抵用券费用
	 */
	public void setDeliveryFeePaidByCoupon(Double deliveryFeePaidByCoupon) {
		this.deliveryFeePaidByCoupon = deliveryFeePaidByCoupon;
	}

	/**
	 * 获取父订单号
	 * @return parentSoCode 父订单号
	 */
	public String getParentSoCode() {
		return parentSoCode;
	}

	/**
	 * 设置父订单号
	 * @param parentSoCode  设置父订单号
	 */
	public void setParentSoCode(String parentSoCode) {
		this.parentSoCode = parentSoCode;
	}

    public Date getDepositPaidTime(){
        return depositPaidTime;
    }

    public void setDepositPaidTime(Date depositPaidTime){
        this.depositPaidTime = depositPaidTime;
    }

    public Date getFinalStartPayTime(){
        return finalStartPayTime;
    }

    public void setFinalStartPayTime(Date finalStartPayTime){
        this.finalStartPayTime = finalStartPayTime;
    }
    
	/**
	 * 获取订单删除状态
	 * @return deleteStatus 删除状态 
	 */
	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	/**
	 * 设置订单删除状态
	 * @param deleteStatus 删除状态
	 */
	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	/**
	 * @return the 行邮税金额
	 */
	public Double getTravelBaggageTaxAmount() {
		return travelBaggageTaxAmount;
	}

	/**
	 * @param 行邮税金额 the travelBaggageTaxAmount to set
	 */
	public void setTravelBaggageTaxAmount(Double travelBaggageTaxAmount) {
		this.travelBaggageTaxAmount = travelBaggageTaxAmount;
	}

	/**
	 *  获取门店ID
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 设置门店ID
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	/**
	 *  获取自动收货天数
	 */
	public Integer getAutoReceiveDays() {
		return autoReceiveDays;
	}

	/**
	 *  设置自动收货天数
	 */
	public void setAutoReceiveDays(Integer autoReceiveDays) {
		this.autoReceiveDays = autoReceiveDays;
	}

	public Integer getDeliveryFeeInsuranceType() {
		return deliveryFeeInsuranceType;
	}

	public void setDeliveryFeeInsuranceType(Integer deliveryFeeInsuranceType) {
		this.deliveryFeeInsuranceType = deliveryFeeInsuranceType;
	}

	public Double getDeliveryFeeInsuranceAmount() {
		return deliveryFeeInsuranceAmount;
	}

	public void setDeliveryFeeInsuranceAmount(Double deliveryFeeInsuranceAmount) {
		this.deliveryFeeInsuranceAmount = deliveryFeeInsuranceAmount;
	}
	
	/**
	 * 获取是否为历史订单标识
	 * @return the isHistory 是否为历史订单标识（0， 近期订单；1，历史订单。）
	 */
	public Integer getIsHistory() {
		return isHistory;
	}

	/**
	 * 设置是否为历史订单标识
	 * @param isHistory 是否为历史订单标识（0， 近期订单；1，历史订单。）
	 */
	public void setIsHistory(Integer isHistory) {
		this.isHistory = isHistory;
	}
	
	public static So createSo(){
		So so = new So();
		so.setId(6971624L);
		so.setEndUserId(2947586L);
		so.setOrderAmount(202.00d);
		so.setOrderCode("10103086AFCC");
		so.setOrderStatus(38);
		so.setOrderType(3);
		so.setBusinessType(0);
		so.setOrderNeedCs(0L);
		so.setOrderPaymentConfirmDate(new Date());
		so.setOrderPaymentSignal(0);
		so.setOrderCreateTime(new Date());
		so.setParentSoId(0L);
		so.setGoodReceiverMobile("13971452705");
		so.setGoodReceiverAddress("武汉关山大道光谷软件园F6栋F8");
		so.setGoodReceiverName("邓桥");
		return so;
	}
}