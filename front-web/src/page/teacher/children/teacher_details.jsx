import { Card, Form, Button, Input, Upload, message } from "antd"
import { LoadingOutlined, PlusOutlined } from "@ant-design/icons"
import { useEffect, useState } from "react";
import config from "../../../config";
import api from "../../../constants/api";
import { getTeacherDetails, updateTeacherDetails } from "../../../utils/interface";


const getBase64 = (img, callback) => {
  
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
};

const beforeUpload = (file) => {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
  if (!isJpgOrPng) {
    message.error('You can only upload JPG/PNG file!');
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('Image must smaller than 2MB!');
  }
  return isJpgOrPng && isLt2M;
};

const TeacherDetails = () => {
  const [loading, setLoading] = useState(false);
  const [imageUrl, setImageUrl] = useState();
  const [form] = Form.useForm()
  useEffect(() => {
    getTeacherDetails().then(res => {
      setImageUrl(res.data.avatar)
      form.setFieldsValue(res.data)
    })
  }, [form])

  const onFinish = (values) => {
    updateTeacherDetails(values).then(res => {
      message.success(res.msg)
    })
  }

  const handleChange = (info) => {
    if (info.file.status === 'uploading') {
      setLoading(true);
      return;
    }
    if (info.file.status === 'done') {
      getBase64(info.file.originFileObj, (url) => {
        setLoading(false);
        setImageUrl(url);
      });
    }
  }
  const uploadButton = (
    <div>
      {loading ? <LoadingOutlined /> : <PlusOutlined />}
      <div
        style={{
          marginTop: 8,
        }}
      >
        Upload
      </div>
    </div>
  );

  const normFile = (e) => {
    // console.log('Upload event:', e);
    if (Array.isArray(e)) {
      return e;
    }
    return e && e.fileList[0].response;
  };


  return (
    <div>
      <Card>
        <div className="details_form">
          <Form
            form={form}
            labelCol={{
              span: 8,
            }}
            wrapperCol={{
              span: 16,
            }}
            style={{
              maxWidth: 600,
            }}
            initialValues={{
              remember: true,
            }}
            onFinish={onFinish}
            autoComplete="off"
          >
            <Form.Item
              label="照片"
              name="avatar"
              valuePropName="fileList[0].response"
              // 如果没有下面这一句会报错
              getValueFromEvent={normFile}
            >
              <Upload
                maxCount={1}
                accept="image/*"
                listType="picture-card"
                className="avatar-uploader"
                showUploadList={false}
                action={config.baseUrl + api.upload}
                beforeUpload={beforeUpload}
                onChange={handleChange}
              >
                {imageUrl ? (
                  <img
                    src={imageUrl}
                    alt="avatar"
                    style={{
                      width: '100%',
                    }}
                  />
                ) : (
                  uploadButton
                )}
              </Upload>
            </Form.Item>
            <Form.Item
              label="基本信息"
              name="information"
            >
              <Input.TextArea />
            </Form.Item>

            <Form.Item
              label="学习经历"
              name="experience"
            >
              <Input.TextArea />
            </Form.Item>
            <Form.Item
              wrapperCol={{
                offset: 8,
                span: 16,
              }}
            >
              <Button className="btn" type="primary" htmlType="submit">
                保存
              </Button>
              <Button className="btn" htmlType="reset">
                重置
              </Button>
            </Form.Item>
          </Form>
        </div>
      </Card>
    </div>
  )
}

export default TeacherDetails